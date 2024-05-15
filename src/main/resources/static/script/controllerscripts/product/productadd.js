//configure the GUI and window parameters
initLayout("Product", "Product Add");
sidebarLoader("/product");

loadOptionVal("/productsizes/findall/exist", "productsize_id", "productsize_name", "Product Type");
loadOptionVal("/producttypes/findall/exist", "producttype_id", "producttype_name", "Product Size");

var jsonList = [
    {
        "str" : "Product size",
        "url" : "/productsizes"
    },
    {
        "str" : "Product type",
        "url" : "/producttypes"
    },
    {
        "str" : "Product",
        "url" : "/product",
        "status" : "active",
    },
]
createThinLongDiv(jsonList);


