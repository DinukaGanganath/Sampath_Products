fetch("/products/findall")
.then(function(response){
    return response.json();
})
.then(function(products){
    let placeholder = document.querySelector("#data-output");
    let out = "";
    for(let product of products){
        out += `
            <tr>
                <td>${product.product_id}</td>
                <td>${product.product_name}</td>
                <td>${product.product_unit_quantity} g</td>
                <td> Rs.${product.product_unit_price}</td>
                <td>${product.product_usable_time} months</td>
            </tr>
        `;
    }
    placeholder.innerHTML = out;
})

