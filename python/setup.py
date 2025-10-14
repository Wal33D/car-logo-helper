#!/usr/bin/env python3
"""Setup script for automotive-logo-library Python package."""

from setuptools import setup, find_packages

setup(
    name="automotive-logo-library",
    version="1.0.0",
    author="Waleed Judah",
    author_email="aquataze@yahoo.com",
    description="A lightweight library for mapping car manufacturers to their logos and decoding VINs",
    url="https://github.com/wal33d/automotive-logo-library",
    packages=find_packages(),
    classifiers=[
        "Development Status :: 4 - Beta",
        "Intended Audience :: Developers",
        "Topic :: Software Development :: Libraries :: Python Modules",
        "Topic :: Utilities",
        "License :: OSI Approved :: MIT License",
        "Programming Language :: Python :: 3",
        "Programming Language :: Python :: 3.8",
        "Programming Language :: Python :: 3.9",
        "Programming Language :: Python :: 3.10",
        "Programming Language :: Python :: 3.11",
        "Programming Language :: Python :: 3.12",
    ],
    python_requires=">=3.8",
    keywords="automotive car logo vin decoder manufacturer brand",
    project_urls={
        "Bug Reports": "https://github.com/wal33d/automotive-logo-library/issues",
        "Source": "https://github.com/wal33d/automotive-logo-library",
    },
    include_package_data=True,
    package_data={
        "": ["../../assets/logos/*.png"],
    },
)
