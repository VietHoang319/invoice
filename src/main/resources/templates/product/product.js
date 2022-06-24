function getAllProduct() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/products",
        success: function (product) {
            console.log(product.content)
            displayProduct(product.content);
        }
    })
}

function displayProduct(array) {
    let str = "";
    for (let i = 0; i < array.length; i++) {
        str += `<tr>
        <td>${array[i].name}</td>
        <td>${array[i].price}</td>
        <td>${array[i].quantity}</td>
        <td><button type="button" class="btn btn-primary" onclick="showModal(${array[i].id})">Sửa</button></td>
        <td><button type="button" class="btn btn-danger" onclick="deleteP(${array[i].id})">Xóa</button></td>
    </tr>`
    }
    // document.getElementById("product_table").innerHTML += str;
    $('#product_table').html(str)
}

function save() {
    let id = $('#idP')
    let name = $('#name')
    let price = $('#price')
    let quantity = $('#quantity')
    let product = {
        name: name.val(),
        price: price.val(),
        quantity: quantity.val()
    }
    console.log(id.val())
    if(id.val() == "") {
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: 'Post',
            url: "http://localhost:8080/api/products",
            data: JSON.stringify(product),
            success: function () {
                name.val("");
                price.val("");
                quantity.val("");
                $('#exampleModal').modal('hide');
                getAllProduct();
            }
        })
    }
    else {
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: 'Put',
            url: "http://localhost:8080/api/products/" + id.val(),
            data: JSON.stringify(product),
            success: function () {
                name.val("");
                price.val("");
                quantity.val("");
                $('#exampleModal').modal('hide');
                getAllProduct();
            }
        })
    }
}

function deleteP(id) {
    $.ajax({
        type: 'delete',
        url: "http://localhost:8080/api/products/" + id,
        success: function () {
            getAllProduct();
        }
    })
}

function showModal(id) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/products/" + id,
        success: function (product) {
            let id = $('#idP')
            let name = $('#name')
            let price = $('#price')
            let quantity = $('#quantity')
            id.val(product.id);
            name.val(product.name);
            price.val(product.price);
            quantity.val(product.quantity);
            let productOut = {
                name: name.val(),
                price: price.val(),
                quantity: quantity.val()
            }
            $('#exampleModal').modal('show');
        }
    })
}
