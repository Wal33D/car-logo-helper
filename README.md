# Automotive Logo Library

[![Python CI](https://github.com/wal33d/automotive-logo-library/actions/workflows/python-ci.yml/badge.svg)](https://github.com/wal33d/automotive-logo-library/actions/workflows/python-ci.yml)
[![Java CI](https://github.com/wal33d/automotive-logo-library/actions/workflows/java-ci.yml/badge.svg)](https://github.com/wal33d/automotive-logo-library/actions/workflows/java-ci.yml)
[![Android CI](https://github.com/wal33d/automotive-logo-library/actions/workflows/android-ci.yml/badge.svg)](https://github.com/wal33d/automotive-logo-library/actions/workflows/android-ci.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![Python Version](https://img.shields.io/badge/python-3.8%2B-blue)](https://www.python.org/downloads/)
[![Java Version](https://img.shields.io/badge/java-8%2B-orange)](https://www.oracle.com/java/)

Lightweight helpers to map car manufacturers to logo assets and decode Vehicle Identification Numbers (VINs). Available for Python, Java, and Android.

## Features

- Map manufacturer names to logo files
- Decode VINs to identify manufacturers using WMI (World Manufacturer Identifier)
- Support for 60+ car manufacturers
- Case-insensitive and partial matching
- Available in both Java and Python

## Supported Manufacturers

The library includes logos for major automotive manufacturers including:
- Mercedes-Benz, BMW, Audi, Volkswagen, Porsche
- Toyota, Honda, Nissan, Mazda, Subaru, Mitsubishi
- Ford, Chevrolet, GMC, Dodge, Jeep, Ram
- Tesla, Lexus, Acura, Infiniti, Genesis
- Jaguar, Land Rover, Volvo, Mini
- Ferrari, Lamborghini, Maserati, Alfa Romeo
- And many more...

## Installation

### Python

```bash
# Install from source
git clone https://github.com/wal33d/automotive-logo-library.git
cd automotive-logo-library/python
pip install -e .
```

Note: When published to PyPI, the distribution name will be `automotive-logo-library` and the import name remains `carlogohelper`.

### Java

Copy `java/com/automotivelogolibrary/AutomotiveLogoLibrary.java` into your project, or compile directly:

```bash
cd java
javac com/automotivelogolibrary/AutomotiveLogoLibrary.java
```

Use on the classpath as needed for your project setup.

### Android

A ready-to-use Android library module is included at `android/automotive-logo-library-android`.

- Option 1: Import the module into your Android project (File → New → Import Module...) and select `android/automotive-logo-library-android`.
- Option 2: Add as a composite build or submodule and include `":automotive-logo-library-android"` in your settings.

Requirements: `compileSdk 34`, `minSdk 21`.

## Usage

### Java

```java
import com.automotivelogolibrary.AutomotiveLogoLibrary;

// Get manufacturer from VIN
String manufacturer = AutomotiveLogoLibrary.getManufacturerFromVIN("WDB2020331A123456");
// Returns: "MERCEDES-BENZ"

// Get logo path (pass assets dir)
Path logoPath = AutomotiveLogoLibrary.getLogoPath("BMW", "path/to/assets/logos");
// Returns: Path to logo_bmw.png

// Check if logo exists
boolean hasLogo = AutomotiveLogoLibrary.hasLogo("Toyota");
// Returns: true

// Get logo filename
String filename = AutomotiveLogoLibrary.getLogoFilename("Tesla");
// Returns: "logo_tesla.png"
```

### Python

```python
from carlogohelper import CarLogoHelper

# Initialize with assets directory
helper = CarLogoHelper("path/to/assets/logos")

# Get manufacturer from VIN
manufacturer = helper.get_manufacturer_from_vin("WDB2020331A123456")
# Returns: "MERCEDES-BENZ"

# Get logo path
logo_path = helper.get_logo_path("BMW")
# Returns: Path to logo_bmw.png

# Check if logo exists
has_logo = helper.has_logo("Toyota")
# Returns: True

# Get logo URL for web apps
logo_url = helper.get_logo_url("Tesla")
# Returns: "https://raw.githubusercontent.com/wal33d/automotive-logo-library/main/assets/logos/logo_tesla.png"

# Get all supported manufacturers
manufacturers = helper.get_supported_manufacturers()
```

### Android

```java
import com.automotivelogolibrary.AutomotiveLogoLibraryAndroid;

// Decode VIN to manufacturer
String make = AutomotiveLogoLibraryAndroid.getManufacturerFromVIN("WDB2020331A123456");

// Get the logo as a Drawable
Drawable logo = AutomotiveLogoLibraryAndroid.getLogoDrawable(context, "BMW");

// Or open an InputStream to the asset
InputStream is = AutomotiveLogoLibraryAndroid.openLogoStream(context, "Toyota");

// Or build a URI to the asset for image loaders
Uri uri = AutomotiveLogoLibraryAndroid.getLogoAssetUri("Tesla"); // file:///android_asset/logos/logo_tesla.png

// Check availability
boolean hasLogo = AutomotiveLogoLibraryAndroid.hasLogo(context, "Mercedes-Benz");

// List supported manufacturers
String[] supported = AutomotiveLogoLibraryAndroid.getSupportedManufacturers();
```

## API Reference

For a summary of available functions and methods in both languages, see `docs/API.md`.

### Using functions directly (Python)

```python
from carlogohelper import (
    get_manufacturer_from_vin,
    get_logo_filename,
    has_logo
)

# Decode VIN
manufacturer = get_manufacturer_from_vin("1HGCM82633A123456")
# Returns: "HONDA"

# Get logo filename
filename = get_logo_filename("Mercedes")
# Returns: "logo_mercedes_benz.png"

# Check if logo exists
exists = has_logo("Ferrari")
# Returns: True
```

## VIN Decoding

The library can decode Vehicle Identification Numbers (VINs) to identify the manufacturer:

- Uses WMI (World Manufacturer Identifier) - the first 3 characters of a VIN
- Supports fallback to 2-character manufacturer codes
- Handles VINs from North American, European, and Asian manufacturers

Example VIN prefixes:
- `WDB`, `WDD` → Mercedes-Benz
- `WBA`, `WBS` → BMW
- `1HG`, `2HG` → Honda
- `1G1` → Chevrolet
- `5YJ` → Tesla

## Logo Files

All logo files are PNG images located in the `assets/logos/` directory. Logos are named using the pattern: `logo_[manufacturer].png`

## Project Structure

```
automotive-logo-library/
├── README.md
├── LICENSE
├── .gitignore
├── assets/
│   └── logos/           # Logo PNG files
├── android/
│   ├── build.gradle     # Android project build
│   ├── settings.gradle  # Includes :automotive-logo-library-android
│   └── automotive-logo-library-android/
│       ├── build.gradle
│       └── src/main/
│           ├── AndroidManifest.xml
│           └── java/com/automotivelogolibrary/AutomotiveLogoLibraryAndroid.java
├── java/
│   └── com/
│       └── automotivelogolibrary/
│           └── AutomotiveLogoLibrary.java
├── python/
│   ├── setup.py
│   ├── pyproject.toml
│   └── carlogohelper/
│       └── __init__.py
└── .github/
    └── workflows/       # CI/CD workflows
```

## License

This project is licensed under the MIT License. See `LICENSE`.

## Legal

All trademarks, logos, and brand names are the property of their respective owners. Logos in `assets/logos/` are provided for identification and educational purposes only and may be subject to additional rights. Ensure you have the right to use these assets in your application.

## Contributing

Contributions are welcome! See `CONTRIBUTING.md` for guidelines, including how to add a new manufacturer, run checks, and open pull requests.

## Author

Waleed Judah (Wal33D)
