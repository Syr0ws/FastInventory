# CraftVentory

![link](https://img.shields.io/badge/API-Spigot-blue) ![link](https://img.shields.io/badge/Version-1.17+-yellow)

**CraftVentory** is a **Java / Spigot** library that facilitates the development of **Minecraft inventories**. 
It enables **developers** to define **fully configurable** inventories in **configuration files** and provide 
them with **Java code** in **their plugins**.

This library was developed to facilitate the development of in-game inventories. Indeed, making them **fully configurable** 
is an important feature for **server administrators** but this process is very tedious without an appropriate tool. 
CraftVentory solves this problem by providing a lot of **built-in features** to help **developers** implement 
**fully customizable** inventories very easily.

## Features

CraftVentory comes with the following features:
- Fully customizable **inventories / items / paginations** from configuration files (**YAML** support).
- Fully customizable **actions** when **clicking** on **items** like sending messages / sounds, executing commands, inventory navigation, etc.
- **Paginations** to paginate a large list of results in an inventory.
- **Placeholders** to display custom values in texts (inventory title, item name / lore, etc.).
- **Inventory history** to enable players to easily navigate between inventories (home, backward, forward).
- **I18n** support.
- **Enhancements** to dynamically modify inventory properties with Java code.
- **Hooks** to execute custom Java code for an inventory when specific events happen.

## Documentation

The documentation of the library is available on its [wiki](https://syrows-development.gitbook.io/craftventory/).

## How to use CraftVentory?

To use CraftVentory, you can directly include the library JAR file as a dependency of your plugin. This JAR file can be downloaded
on the [Releases page](https://github.com/Syr0ws/CraftVentory/releases) of the GitHub repository of the project.

The library can also be included in your project by using a dependency manager like **Maven** or **Gradle**.

**Maven**
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>

<dependency>
    <groupId>com.github.Syr0ws</groupId>
    <artifactId>CraftVentory</artifactId>
    <version>{VERSION}</version>
    <scope>compile</scope>
</dependency>
```

**Gradle**
```json

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.Syr0ws:CraftVentory:{VERSION}'
}
```

After being added as dependency, you must initialize the library by following this [tutorial](https://syrows-development.gitbook.io/craftventory/get-started/initialize-the-library).
