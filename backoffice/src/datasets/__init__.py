import os

LOCAL = os.path.relpath(os.path.dirname(__file__))
CATEGORIES_PATH = f"{LOCAL}{os.sep}category.csv"
PRODUCTS_PATH = f"{LOCAL}{os.sep}product.csv"
VERSION_TABLES_PATH = f"{LOCAL}{os.sep}version_tables.json"
LIST_PATH = f"{LOCAL}{os.sep}lists{os.sep}"
