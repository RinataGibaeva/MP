function getAllProducts() {
    $.ajax({
            url: "http://localhost:8080/products",
            type: "GET",
            contentType: "application/json",
            crossDomain: true,
            dataType: 'json',
            async: false,
            headers: {
                'Auth-Token': $.cookie('Auth-Token')
            },
            xhrFields: {
                withCredentials: true
            },
            statusCode: {
                500: function (xhr) {
                    window.location = '/login.html';
                },
                200: function (xhr) {
                    // преобразовали его в json-объект
                    let data = xhr;
                    // получили таблицу из html-страницы
                    let table = document.getElementById('product_book_table');
                    // бежим по всем элементам массива
                    for (let i = 0; i < data.length; i++) {
                        // вставляем строку в таблицу и сразу ее получаем
                        let row = table.insertRow(i + 1);
                        // создаем набор ячеек
                        const cellName = row.insertCell(0);
                        const cellPhone = row.insertCell(1);
                        const cellPhoto = row.insertCell(2);
                        // задаем текст для каждой ячейки
                        cellPhone.innerHTML = data[i]["price"];
                        cellName.innerHTML = data[i]["product_name"];
                        cellPhoto.innerHTML = "<img width='200' src=\"" + data[i]["photoUrl"] + "\">";
                    }
                }
            }
        }
    )
}

function sendProductData(product_name, price, photo_url) {
    // создали json-объект
    let json = {};
    // положили туда данные
    json["product_name"] = product_name;
    json["price"] = price;
    json["photoUrl"] = photo_url;
    $.ajax({
            url: "http://localhost:8080/products",
            type: "POST",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(json),
            headers: {
                'Auth-Token': $.cookie('Auth-Token')
            },
            statusCode: {
                200: function (xhr) {
                    // преобразовали его в json-объект
                    let data = xhr;
                    // получили таблицу из html-страницы
                    let table = document.getElementById('product_book_table');
                    // бежим по всем элементам массива
                    let rowsCount = table.getElementsByTagName('tr').length;
                    let row = table.insertRow(rowsCount);
                    const cellName = row.insertCell(0);
                    const cellPhone = row.insertCell(1);
                    const cellPhoto = row.insertCell(2);
                    // задаем текст для каждой ячейки
                    cellPhone.innerHTML = data["price"];
                    cellName.innerHTML = data["product_name"];
                    cellPhoto.innerHTML = "<img width='200' src=\"" + data["photoUrl"] + "\">";
                }
            }
        }
    )
}