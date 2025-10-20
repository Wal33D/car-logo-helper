# API Reference

This document summarizes the public API for Python, Java, and Android implementations of Automotive Logo Library.

## Python (`carlogohelper`)

Import path: `from carlogohelper import ...`

- `get_manufacturer_from_vin(vin: str) -> Optional[str]`
  - Returns the manufacturer name based on a VIN using WMI prefixes.

- `get_logo_path(input_str: str, assets_dir: str) -> Optional[pathlib.Path]`
  - Accepts a manufacturer name or VIN and returns a filesystem path to the logo file if found.

- `get_logo_filename(input_str: str) -> Optional[str]`
  - Returns the logo filename (e.g., `logo_bmw.png`) for a manufacturer name or VIN.

- `has_logo(input_str: str) -> bool`
  - Returns `True` if a logo exists for the given manufacturer name or VIN.

- `get_supported_manufacturers() -> List[str]`
  - Returns all keys present in the internal logo map (upper‑case names and aliases).

- `get_logo_url(input_str: str, base_url: str = "https://raw.githubusercontent.com/wal33d/automotive-logo-library/main/assets/logos/") -> Optional[str]`
  - Returns a URL to the logo asset for web use. Customize `base_url` if self‑hosting.

- Class `CarLogoHelper(assets_dir: Optional[str] = None)`
  - Methods mirror the module‑level functions:
    - `get_manufacturer_from_vin(vin) -> Optional[str]`
    - `get_logo_path(input_str) -> Optional[Path]`
    - `get_logo_filename(input_str) -> Optional[str]`
    - `has_logo(input_str) -> bool`
    - `get_supported_manufacturers() -> List[str]`

Example:

```python
from carlogohelper import CarLogoHelper

helper = CarLogoHelper("path/to/assets/logos")
print(helper.get_manufacturer_from_vin("5YJ3E1EA3JF000001"))  # TESLA
print(helper.get_logo_filename("Mercedes-Benz"))               # logo_mercedes_benz.png
print(helper.has_logo("BMW"))                                  # True
```

## Java (`com.automotivelogolibrary.AutomotiveLogoLibrary`)

Static methods:

- `String getManufacturerFromVIN(String vin)`
  - Returns the manufacturer name based on a VIN using WMI prefixes.

- `Path getLogoPath(String input, String assetsDir)`
  - Accepts a manufacturer name or VIN and returns a filesystem path to the logo file if found.

- `String getLogoFilename(String input)`
  - Returns the logo filename for a manufacturer name or VIN.

- `boolean hasLogo(String input)`
  - Returns `true` if a logo exists for the given manufacturer name or VIN.

- `String[] getSupportedManufacturers()`
  - Returns all keys present in the internal logo map (upper‑case names and aliases).

Example:

```java
import com.automotivelogolibrary.AutomotiveLogoLibrary;

String make = AutomotiveLogoLibrary.getManufacturerFromVIN("WDB2020331A123456"); // MERCEDES-BENZ
String file = AutomotiveLogoLibrary.getLogoFilename("BMW");                       // logo_bmw.png
boolean ok = AutomotiveLogoLibrary.hasLogo("Toyota");                             // true

## Android (`com.automotivelogolibrary.AutomotiveLogoLibraryAndroid`)

Static helpers for assets bundled under `assets/logos/`:

- `String getManufacturerFromVIN(String vin)`
- `String getLogoFilename(String input)`
- `boolean hasLogo(Context context, String input)`
- `InputStream openLogoStream(Context context, String input)`
- `Drawable getLogoDrawable(Context context, String input)`
- `String getLogoAssetPath(String input)`
- `Uri getLogoAssetUri(String input)`
```

## Matching Rules

- Case‑insensitive comparisons.
- Partial/alias matching (e.g., `MERCEDES`, `MERCEDES BENZ`, `MERCEDES-BENZ`).
- VIN decoding prioritizes 3‑character WMI, then 2‑character fallbacks.

## Assets

- Logos are PNG files under `assets/logos/`.
- Naming: `logo_[lowercase_name_with_underscores].png`.
- Ensure you have rights to use logos in your application.
