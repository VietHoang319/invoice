let pageable = $("#pageable")
let valiName = $('#valiName')
let valiQuantity = $('#valiQuantity')
let id = $('#idP')
let name = $('#name')
let price = $('#price')
let quantity = $('#quantity')
function getAllProduct(number) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/products?page=" + number,
        success: function (product) {
            console.log(product)
            let str = ""
            displayProduct(product.t.content);
            let number1 = product.t.pageable.pageNumber;
            if (product.t.pageable.pageNumber > 0 && product.t.pageable.pageNumber+1 === product.t.totalPages) {
                str += `<button onclick="getAllProduct(${number1 - 1})">Trước</button> `;
            }
            str += product.t.pageable.pageNumber+1 + "/" + product.t.totalPages
            if (product.t.pageable.pageNumber <= 0 && product.t.pageable.pageNumber+1 !== product.t.totalPages) {
                str += ` <button onclick="getAllProduct(${number1 + 1})">Sau</button>`;
            }
            pageable.html(str)
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
                getAllProduct(0);
            },
            error: function (error) {
                valiName.text(error.responseJSON.name)
                valiQuantity.text(error.responseJSON.quantity)
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
                getAllProduct(0);
            }
        })
    }
}

function deleteP(id) {
    $.ajax({
        type: 'delete',
        url: "http://localhost:8080/api/products/" + id,
        success: function () {
            getAllProduct(0);
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

function resetData() {
    valiName.text("")
    valiQuantity.text("")
    id.val("")
    name.val("")
    price.val("")
    quantity.val("")
}
