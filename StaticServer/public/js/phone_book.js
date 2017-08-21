function getAllUsers() {
    $.ajax({
            url: "http://localhost:8080/records",
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
                    let table = document.getElementById('phone_book_table');
                    // бежим по всем элементам массива
                    for (let i = 0; i < data.length; i++) {
                        // вставляем строку в таблицу и сразу ее получаем
                        let row = table.insertRow(i + 1);
                        // создаем набор ячеек
                        const cellName = row.insertCell(0);
                        const cellPhone = row.insertCell(1);
                        // задаем текст для каждой ячейки
                        cellPhone.innerHTML = data[i]["phone"];
                        cellName.innerHTML = data[i]["name"];
                    }
                }
            }
        }
    )
}

function sendRecord(name, phone) {
    // создали json-объект
    let json = {};
    // положили туда данные
    json["name"] = name;
    json["phone"] = phone;
    $.ajax({
            url: "http://localhost:8080/records",
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
                    let table = document.getElementById('phone_book_table');
                    // бежим по всем элементам массива
                    let rowsCount = table.getElementsByTagName('tr').length;
                    let row = table.insertRow(rowsCount);
                    const cellName = row.insertCell(0);
                    const cellPhone = row.insertCell(1);
                    // задаем текст для каждой ячейки
                    cellPhone.innerHTML = data["phone"];
                    cellName.innerHTML = data["name"];
                }
            }
        }
    )
}