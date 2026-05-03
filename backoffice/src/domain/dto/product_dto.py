from pydantic import BaseModel


class ProductDTO(BaseModel):
    id: str
    name: str
    category_id: str
    created_at: str
    updated_at: str
