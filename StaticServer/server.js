// подключаем библиотеку express
// express - библиотека, позволяюща настроить
// сервер для отдачи html-страниц и прочих ресурсов
// require - метод NodeJs, который позволяет
// подключить библиотеку
let express = require('express');

// создаем наш статик-сервер
let app = express();

// говорим, что мы раздаем статику из папки public
app.use(express.static('public'));
// запускаем сервер на порту 8081
app.listen(8085);
console.log("StaticServer started...");