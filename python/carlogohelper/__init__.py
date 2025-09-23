#!/usr/bin/env python3
"""
Car Logo Helper - Map car manufacturers to their logos and decode VINs
"""

import os
import re
from pathlib import Path
from typing import Optional, List, Union

# Logo filename mappings
LOGO_MAP = {
    "MERCEDES-BENZ": "logo_mercedes_benz.png",
    "MERCEDES BENZ": "logo_mercedes_benz.png",
    "MERCEDES": "logo_mercedes_benz.png",
    "BMW": "logo_bmw.png",
    "AUDI": "logo_audi.png",
    "TOYOTA": "logo_toyota.png",
    "HONDA": "logo_honda.png",
    "FORD": "logo_ford.png",
    "CHEVROLET": "logo_chevrolet.png",
    "CHEVY": "logo_chevrolet.png",
    "VOLKSWAGEN": "logo_volkswagen.png",
    "VW": "logo_volkswagen.png",
    "NISSAN": "logo_nissan.png",
    "HYUNDAI": "logo_hyundai.png",
    "MAZDA": "logo_mazda.png",
    "SUBARU": "logo_subaru.png",
    "PORSCHE": "logo_porsche.png",
    "TESLA": "logo_tesla.png",
    "LEXUS": "logo_lexus.png",
    "ACURA": "logo_acura.png",
    "INFINITI": "logo_infiniti.png",
    "JAGUAR": "logo_jaguar.png",
    "LAND ROVER": "logo_land_rover.png",
    "LAND-ROVER": "logo_land_rover.png",
    "VOLVO": "logo_volvo.png",
    "MITSUBISHI": "logo_mitsubishi.png",
    "KIA": "logo_kia.png",
    "GENESIS": "logo_genesis.png",
    "GMC": "logo_gmc.png",
    "JEEP": "logo_jeep.png",
    "RAM": "logo_ram.png",
    "DODGE": "logo_dodge.png",
    "CHRYSLER": "logo_chrysler.png",
    "BUICK": "logo_buick.png",
    "CADILLAC": "logo_cadillac.png",
    "LINCOLN": "logo_lincoln.png",
    "MINI": "logo_mini.png",
    "FIAT": "logo_fiat.png",
    "ALFA ROMEO": "logo_alfa_romeo.png",
    "MASERATI": "logo_maserati.png",
    "FERRARI": "logo_ferrari.png",
    "LAMBORGHINI": "logo_lamborghini.png",
    "BENTLEY": "logo_bentley.png",
    "ROLLS ROYCE": "logo_rolls_royce.png",
    "ROLLS-ROYCE": "logo_rolls_royce.png",
    "ASTON MARTIN": "logo_aston_martin.png",
    "PEUGEOT": "logo_peugeot.png",
    "RENAULT": "logo_renault.png",
    "CITROEN": "logo_citroen.png",
    "SEAT": "logo_seat.png",
    "SKODA": "logo_skoda.png",
    "OPEL": "logo_opel.png"
}

# VIN WMI (World Manufacturer Identifier) mappings
# First 3 characters of VIN identify the manufacturer
VIN_WMI_MAP = {
    # North American manufacturers
    "1FA": "FORD", "1FB": "FORD", "1FC": "FORD", "1FD": "FORD", "1FM": "FORD", "1FT": "FORD",
    "1G1": "CHEVROLET", "1G2": "PONTIAC", "1G3": "OLDSMOBILE", "1G4": "BUICK", "1G6": "CADILLAC",
    "1GC": "CHEVROLET", "1GM": "PONTIAC", "1HG": "HONDA", "1L1": "LINCOLN", "1ME": "MERCURY",
    "1VW": "VOLKSWAGEN", "1YV": "MAZDA",
    "2FA": "FORD", "2FB": "FORD", "2FC": "FORD", "2FM": "FORD", "2FT": "FORD",
    "2G1": "CHEVROLET", "2G2": "PONTIAC", "2HG": "HONDA", "2HK": "HONDA", "2HM": "HONDA",
    "3FA": "FORD", "3FB": "FORD", "3FC": "FORD", "3FE": "FORD", "3G1": "CHEVROLET",
    "3VW": "VOLKSWAGEN",
    "4F2": "MAZDA", "4M2": "MERCURY", "4S3": "SUBARU", "4S4": "SUBARU",
    "4T1": "TOYOTA", "4T3": "TOYOTA", "4US": "BMW",
    "5FN": "HONDA", "5L1": "LINCOLN", "5LM": "LINCOLN", "5N1": "NISSAN", "5NP": "HYUNDAI",
    "5TB": "TOYOTA", "5TD": "TOYOTA", "5TF": "TOYOTA", "5UX": "BMW", "5YJ": "TESLA",

    # Japanese manufacturers
    "JA3": "MITSUBISHI", "JA4": "MITSUBISHI", "JA7": "MITSUBISHI",
    "JF1": "SUBARU", "JF2": "SUBARU", "JHM": "HONDA",
    "JM1": "MAZDA", "JM3": "MAZDA", "JN1": "NISSAN", "JN8": "NISSAN",
    "JT2": "TOYOTA", "JT3": "TOYOTA", "JTD": "TOYOTA", "JTE": "TOYOTA",
    "JTH": "LEXUS", "JTJ": "LEXUS", "JTK": "LEXUS", "JTL": "LEXUS",
    "JTM": "TOYOTA", "JTN": "TOYOTA",

    # Korean manufacturers
    "KL1": "CHEVROLET", "KL2": "DAEWOO", "KL4": "BUICK",
    "KM8": "HYUNDAI", "KMF": "HYUNDAI", "KMH": "HYUNDAI",
    "KMJ": "GENESIS", "KMX": "GENESIS", "KNA": "KIA", "KND": "KIA",

    # European manufacturers
    "SAJ": "JAGUAR", "SAL": "LAND ROVER", "SAR": "ROVER",
    "SCA": "ROLLS ROYCE", "SCB": "BENTLEY", "SCC": "LOTUS",
    "SCE": "DELOREAN", "SCF": "ASTON MARTIN",
    "TRU": "AUDI",
    "VF1": "RENAULT", "VF2": "RENAULT", "VF3": "PEUGEOT", "VF6": "RENAULT",
    "VF7": "CITROEN", "VF8": "MATRA", "VS6": "FORD", "VSS": "SEAT", "VV1": "RENAULT",
    "WAU": "AUDI", "WAP": "ALPINA",
    "WBA": "BMW", "WBB": "BMW", "WBS": "BMW", "WBW": "BMW", "WBX": "BMW", "WBY": "BMW",
    "WDA": "DAIMLER", "WDB": "MERCEDES-BENZ", "WDC": "MERCEDES-BENZ",
    "WDD": "MERCEDES-BENZ", "WDF": "MERCEDES-BENZ",
    "WMW": "MINI", "W0L": "OPEL", "W0V": "OPEL",
    "WP0": "PORSCHE", "WP1": "PORSCHE", "WUA": "AUDI",
    "WVG": "VOLKSWAGEN", "WVW": "VOLKSWAGEN",
    "WV1": "VOLKSWAGEN", "WV2": "VOLKSWAGEN", "WV3": "VOLKSWAGEN",
    "XTA": "LADA",
    "YK1": "SAAB", "YS2": "SCANIA", "YS3": "SAAB", "YTN": "SAAB",
    "YV1": "VOLVO", "YV2": "VOLVO", "YV3": "VOLVO", "YV4": "VOLVO", "YV5": "VOLVO",

    # Italian manufacturers
    "ZA9": "LAMBORGHINI", "ZAM": "MASERATI", "ZAP": "PIAGGIO", "ZAR": "ALFA ROMEO",
    "ZCF": "IVECO", "ZFA": "FIAT", "ZFC": "FIAT", "ZFF": "FERRARI",
    "ZGA": "IVM", "ZGU": "MASERATI", "ZHW": "LAMBORGHINI", "ZLA": "LANCIA", "ZOM": "OM",

    # Two-letter codes (less specific)
    "1C": "CHRYSLER", "1D": "DODGE", "1F": "FORD", "1G": "GENERAL MOTORS",
    "1H": "HONDA", "1J": "JEEP", "1L": "LINCOLN", "1M": "MERCURY",
    "1N": "NISSAN", "1V": "VOLKSWAGEN", "1Y": "MAZDA",
    "2C": "CHRYSLER", "2D": "DODGE", "2F": "FORD", "2G": "GENERAL MOTORS",
    "2H": "HONDA", "2M": "MERCURY", "2T": "TOYOTA",
    "3C": "CHRYSLER", "3D": "DODGE", "3F": "FORD", "3G": "GENERAL MOTORS",
    "3H": "HONDA", "3N": "NISSAN", "3V": "VOLKSWAGEN",
    "4F": "MAZDA", "4J": "MERCEDES-BENZ", "4M": "MERCURY", "4S": "SUBARU", "4T": "TOYOTA", "4U": "BMW",
    "5F": "HONDA", "5L": "LINCOLN", "5N": "NISSAN", "5T": "TOYOTA", "5U": "BMW", "5Y": "TESLA"
}


def get_manufacturer_from_vin(vin: str) -> Optional[str]:
    """
    Get the manufacturer from a VIN

    Args:
        vin: The Vehicle Identification Number

    Returns:
        The manufacturer name, or None if not found
    """
    if not vin or len(vin) < 3:
        return None

    upper_vin = vin.upper().strip()

    # Try 3-character WMI first
    wmi = upper_vin[:min(3, len(upper_vin))]
    manufacturer = VIN_WMI_MAP.get(wmi)

    # If not found, try 2-character prefix
    if not manufacturer and len(upper_vin) >= 2:
        prefix = upper_vin[:2]
        manufacturer = VIN_WMI_MAP.get(prefix)

    return manufacturer


def get_logo_path(input_str: str, assets_dir: str) -> Optional[Path]:
    """
    Get logo file path for a given manufacturer name or VIN

    Args:
        input_str: Either a manufacturer name or a VIN
        assets_dir: The directory containing the logo assets

    Returns:
        Path to the logo file, or None if not found
    """
    if not input_str:
        return None

    manufacturer = input_str

    # Check if input looks like a VIN (17 characters or 3-17 for partial)
    if 3 <= len(input_str) <= 17:
        vin_manufacturer = get_manufacturer_from_vin(input_str)
        if vin_manufacturer:
            manufacturer = vin_manufacturer

    # Convert to uppercase for case-insensitive lookup
    upper_make = manufacturer.upper().strip()

    # Direct lookup
    logo_file = LOGO_MAP.get(upper_make)

    # Try partial matches if no direct match
    if not logo_file:
        # Remove common suffixes and special characters for better matching
        cleaned_make = re.sub(r'[-\s]', '', upper_make)

        for key, value in LOGO_MAP.items():
            cleaned_key = re.sub(r'[-\s]', '', key)

            # Check if either contains the other (after cleaning)
            if (cleaned_make in cleaned_key or cleaned_key in cleaned_make or
                upper_make in key or key in upper_make):
                logo_file = value
                break

    if not logo_file:
        return None

    # Return the path to the logo file
    return Path(assets_dir) / logo_file


def get_logo_filename(input_str: str) -> Optional[str]:
    """
    Get logo filename for a given manufacturer name or VIN

    Args:
        input_str: Either a manufacturer name or a VIN

    Returns:
        Logo filename, or None if not found
    """
    if not input_str:
        return None

    manufacturer = input_str

    # Check if input looks like a VIN
    if 3 <= len(input_str) <= 17:
        vin_manufacturer = get_manufacturer_from_vin(input_str)
        if vin_manufacturer:
            manufacturer = vin_manufacturer

    # Convert to uppercase for case-insensitive lookup
    upper_make = manufacturer.upper().strip()

    # Direct lookup
    logo_file = LOGO_MAP.get(upper_make)

    # Try partial matches if no direct match
    if not logo_file:
        cleaned_make = re.sub(r'[-\s]', '', upper_make)

        for key, value in LOGO_MAP.items():
            cleaned_key = re.sub(r'[-\s]', '', key)

            if (cleaned_make in cleaned_key or cleaned_key in cleaned_make or
                upper_make in key or key in upper_make):
                logo_file = value
                break

    return logo_file


def has_logo(input_str: str) -> bool:
    """
    Check if a logo exists for the given manufacturer or VIN

    Args:
        input_str: Either a manufacturer name or a VIN

    Returns:
        True if a logo exists
    """
    return get_logo_filename(input_str) is not None


def get_supported_manufacturers() -> List[str]:
    """
    Get a list of all supported manufacturers

    Returns:
        List of manufacturer names
    """
    return list(LOGO_MAP.keys())


def get_logo_url(input_str: str, base_url: str = "https://raw.githubusercontent.com/wal33d/car-logo-helper/main/assets/logos/") -> Optional[str]:
    """
    Get URL to logo for web applications

    Args:
        input_str: Either a manufacturer name or a VIN
        base_url: Base URL where logos are hosted

    Returns:
        Full URL to logo file, or None if not found
    """
    filename = get_logo_filename(input_str)
    if filename:
        return base_url + filename
    return None


class CarLogoHelper:
    """Main class for car logo helper functionality"""

    def __init__(self, assets_dir: str = None):
        """
        Initialize CarLogoHelper

        Args:
            assets_dir: Directory containing logo assets
        """
        self.assets_dir = assets_dir or os.path.join(os.path.dirname(__file__), '../../assets/logos')

    def get_manufacturer_from_vin(self, vin: str) -> Optional[str]:
        """Get manufacturer from VIN"""
        return get_manufacturer_from_vin(vin)

    def get_logo_path(self, input_str: str) -> Optional[Path]:
        """Get path to logo file"""
        return get_logo_path(input_str, self.assets_dir)

    def get_logo_filename(self, input_str: str) -> Optional[str]:
        """Get logo filename"""
        return get_logo_filename(input_str)

    def has_logo(self, input_str: str) -> bool:
        """Check if logo exists"""
        return has_logo(input_str)

    def get_supported_manufacturers(self) -> List[str]:
        """Get list of supported manufacturers"""
        return get_supported_manufacturers()


# Export main functions
__all__ = [
    'CarLogoHelper',
    'get_manufacturer_from_vin',
    'get_logo_path',
    'get_logo_filename',
    'has_logo',
    'get_supported_manufacturers',
    'get_logo_url',
    'LOGO_MAP',
    'VIN_WMI_MAP'
]