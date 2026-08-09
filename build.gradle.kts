plugins {
    eclipse
    idea
    alias(libs.plugins.forgegradle)
    alias(libs.plugins.spotbugs)
    pmd
}

version = "1.0.0"
group = "net.asunacraft.eof"
base.archivesName.set("eof")

java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))

sourceSets {
    main {
        resources.srcDir("src/generated/resources")
    }
}

tasks.processResources {
    val expandProps = mapOf(
        "mod_id" to project.property("mod_id"),
        "mod_name" to project.property("mod_name"),
        "mod_version" to project.property("mod_version"),
        "mod_description" to project.property("mod_description"),
        "mod_authors" to project.property("mod_authors"),
        "forge_version_range" to project.property("forge_version_range"),
        "loader_version_range" to project.property("loader_version_range"),
        "minecraft_version" to libs.versions.minecraft.get()
    )

    inputs.properties(expandProps)
    
    filesMatching("META-INF/mods.toml") {
        expand(expandProps)
    }
}

minecraft {
    mappings("official", libs.versions.mappings.get())

    runs {
        create("client") {
            workingDirectory(project.file("run"))
            
            property("forge.logging.markers", "REGISTRIES")
            property("forge.logging.console.level", "debug")
            
            mods {
                create("eof") {
                    source(sourceSets.main.get())
                }
            }
        }

        create("server") {
            workingDirectory(project.file("run"))
            
            property("forge.logging.markers", "REGISTRIES")
            property("forge.logging.console.level", "debug")
            
            mods {
                create("eof") {
                    source(sourceSets.main.get())
                }
            }
        }

        create("data") {
            workingDirectory(project.file("run"))
            
            property("forge.logging.markers", "REGISTRIES")
            property("forge.logging.console.level", "debug")
            
            args.addAll(listOf(
                "--mod", "eof",
                "--all",
                "--output", file("src/generated/resources/").absolutePath,
                "--existing", file("src/main/resources/").absolutePath
            ))

            mods {
                create("eof") {
                    source(sourceSets.main.get())
                }
            }
        }
    }
}

dependencies {
    "minecraft"("net.minecraftforge:forge:${libs.versions.minecraft.get()}-${libs.versions.forge.get()}")
}

tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.add("-Xlint:all")
}

spotbugs {
    ignoreFailures.set(true)
    effort.set(com.github.spotbugs.snom.Effort.DEFAULT)
    reportLevel.set(com.github.spotbugs.snom.Confidence.LOW)
    excludeFilter = file("config/spotbugs/exclude.xml")
}

tasks.withType<com.github.spotbugs.snom.SpotBugsTask>().configureEach {
    reports {
        create("html") {
            required.set(true)
        }
    }
}

pmd {
    isIgnoreFailures = true
    ruleSetFiles = files("config/pmd/ruleset.xml")
    toolVersion = libs.versions.pmd.get()
}