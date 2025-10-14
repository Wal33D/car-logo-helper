# Contributing to Automotive Logo Library

Thanks for your interest in contributing! This project provides simple, dependency‑free helpers to map car manufacturers to logos and decode VINs in both Python and Java.

The most common contributions are adding manufacturers, improving VIN mappings, and enhancing docs. Please follow these guidelines to keep things consistent.

## Getting Started

- Python: Python 3.8+ is supported. Install in editable mode:
  
  ```bash
  cd python
  pip install -e .
  ```

- Java: Java 8+ is supported. Compile the helper directly:
  
  ```bash
  cd java
  javac com/automotivelogolibrary/AutomotiveLogoLibrary.java
  ```

## Project Layout

```
automotive-logo-library/
├── assets/logos/        # PNG logos (see naming rules below)
├── java/com/automotivelogolibrary/AutomotiveLogoLibrary.java
├── python/carlogohelper/__init__.py
├── .github/workflows/   # CI for Python, Java, Android
├── README.md
└── CONTRIBUTING.md
```

## Adding a Manufacturer

1. Add a PNG logo to `assets/logos/` using the naming convention:
   - `logo_[lowercase_name_with_underscores].png`
   - Examples: `logo_mercedes_benz.png`, `logo_bmw.png`, `logo_land_rover.png`

2. Update the logo map in both implementations:
   - Python: `python/carlogohelper/__init__.py` → `LOGO_MAP`
   - Java: `java/com/automotivelogolibrary/AutomotiveLogoLibrary.java` → `LOGO_MAP`
   - Include common variants/aliases (e.g., `MERCEDES-BENZ`, `MERCEDES BENZ`, `MERCEDES`).

3. If VIN prefixes are known, add WMI codes:
   - Python: `VIN_WMI_MAP` in `python/carlogohelper/__init__.py`
   - Java: `VIN_WMI_MAP` in `java/com/automotivelogolibrary/AutomotiveLogoLibrary.java`
   - Prefer 3‑character WMIs; add 2‑character fallbacks only if needed.

4. Update docs where relevant:
   - Supported manufacturers list in `README.md`
   - API notes in `docs/API.md` (optional)

## Verifying Changes

Quick sanity checks mirror CI steps:

```bash
# Python import and basic checks
python - <<'PY'
from carlogohelper import get_manufacturer_from_vin, get_logo_filename, has_logo
assert get_manufacturer_from_vin('5YJ3E1EA3JF000001') == 'TESLA'
assert has_logo('BMW') is True
assert get_logo_filename('Mercedes-Benz') == 'logo_mercedes_benz.png'
print('Python checks passed')
PY

# Java compilation
cd java && javac com/automotivelogolibrary/AutomotiveLogoLibrary.java && echo 'Java compiled'
```

CI runs on pushes and PRs for multiple Python and Java versions. See `.github/workflows/`.

## Coding Style

- Python: Keep it dependency‑free and PEP 8 compliant. Avoid unnecessary complexity.
- Java: Keep it Java 8+ compatible. Avoid external dependencies.
- Both: Maintain consistent behavior across languages (case‑insensitive, partial matching rules).

## Pull Requests

- Create a focused PR per change (e.g., “Add Peugeot logo and VIN codes”).
- Include a brief description of what and why.
- If adding assets, confirm you have the right to include them.
- Ensure README and/or `docs/API.md` are updated if behavior changes.

## Legal Note

Logos are the property of their respective owners and are included for identification and educational purposes. Confirm usage rights for your downstream application.

## Questions

Open a discussion or issue if anything is unclear. Thanks for contributing!
