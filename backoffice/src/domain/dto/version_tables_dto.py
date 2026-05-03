from pydantic import BaseModel


class VersionTablesDTO(BaseModel):
    products_version: int
    categories_version: int
    updated_at: str