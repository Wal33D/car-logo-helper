# Automotive Logo Library

[![Python CI](https://github.com/wal33d/automotive-logo-library/workflows/Python%20CI/badge.svg)](https://github.com/wal33d/automotive-logo-library/actions)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Python Version](https://img.shields.io/badge/python-3.8%2B-blue)](https://www.python.org/downloads/)
[![Java Version](https://img.shields.io/badge/java-8%2B-orange)](https://www.oracle.com/java/)

A lightweight library for mapping car manufacturers to their logos and decoding Vehicle Identification Numbers (VINs).

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
# Install from PyPI (coming soon)
pip install automotive-logo-library

# Or install from source
git clone https://github.com/wal33d/automotive-logo-library.git
cd automotive-logo-library/python
pip install -e .
```

### Java

Copy the Java implementation from `java/com/carlogohelper/CarLogoHelper.java` into your project.

```xml
<!-- Maven (coming soon) -->
<dependency>
    <groupId>com.carlogohelper</groupId>
    <artifactId>automotive-logo-library</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Usage

### Java

```java
import com.carlogohelper.CarLogoHelper;

// Initialize with assets directory
CarLogoHelper helper = new CarLogoHelper("path/to/assets/logos");

// Get manufacturer from VIN
String manufacturer = helper.getManufacturerFromVIN("WDB2020331A123456");
// Returns: "MERCEDES-BENZ"

// Get logo path
Path logoPath = helper.getLogoPath("BMW");
// Returns: Path to logo_bmw.png

// Check if logo exists
boolean hasLogo = helper.hasLogo("Toyota");
// Returns: true

// Get logo filename
String filename = helper.getLogoFilename("Tesla");
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
# Returns: "https://raw.githubusercontent.com/.../logo_tesla.png"

# Get all supported manufacturers
manufacturers = helper.get_supported_manufacturers()
```

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
├── java/
│   └── com/
│       └── carlogohelper/
│           └── CarLogoHelper.java
├── python/
│   ├── setup.py
│   ├── pyproject.toml
│   └── carlogohelper/
│       └── __init__.py
└── .github/
    └── workflows/       # CI/CD workflows
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

This project is provided as-is for use with OBD-Droid and related automotive applications.

## Author

Waleed Judah (Wal33D)

## Contributing

Feel free to submit issues and enhancement requests.