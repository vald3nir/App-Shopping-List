from src.database.firebase.category_dao import CategoryDAO
from src.database.firebase.product_dao import ProductDAO
from src.database.firebase.version_tables_dao import VersionTablesDAO
from src.database.local.category_csv import CategoryCSV
from src.database.local.product_csv import ProductCSV
from src.database.local.version_tables_json import VersionTablesJson


# --------------------------------------------------------------------------------------------------------------
def backup_version_tables():
    version_tables = VersionTablesDAO().load_version_tables()
    VersionTablesJson().save_version_tables(version_tables)


def backup_products():
    products: list[dict] = ProductDAO().load_products()
    ProductCSV().save_products(products=products)


def backup_category():
    categories: list[dict] = CategoryDAO().load_categories()
    CategoryCSV().save_categories(categories=categories)


# --------------------------------------------------------------------------------------------------------------

def backup_database():
    backup_version_tables()
    backup_products()
    backup_category()
