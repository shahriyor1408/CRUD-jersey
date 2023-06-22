const bookList = document.getElementById('book-list');
const table = document.getElementById('tbody');
const searchParams = new URLSearchParams(window.location.search);
if (!searchParams.get("page")) {
    searchParams.set("page", 1);
}
const page = searchParams.get("page");
const per_page = 5;

const getData = () => {
    let searchInput = document.getElementById("searchInput");
    console.log(searchInput.value);

    fetch(`http://localhost:8080/book_crud_ee_war_exploded/api/books?search=${searchInput.value}`)
        .then(response => response.json())
        .then(books => {
            const page_count = Math.floor(books?.length / per_page) + (books?.length % per_page ? 1 : 0);
            let a = '';
            books?.forEach((e, i) => {
                    if (i >= (page - 1) * per_page && i < page * per_page) {
                        let date1 = new Date(e?.createdAt).toDateString();
                        let date2 = e?.updatedAt;
                        if (date2 === null) {
                            date2 = '';
                        } else {
                            date2 = new Date(e?.updatedAt).toDateString();
                        }
                        a += `<tr>
                                    <th scope="row">${i + 1}</th>
                                    <td>${e?.name}</td>
                                    <td>${e?.author}</td>
                                    <td>${e?.description}</td>
                                    <td>${date1}</td>
                                    <td>${date2}</td>
                                    <td>
              <button class="btn btn-primary btn-sm" onclick="handleClick(${e?.id})" >Update</button>
              <button class="btn btn-danger btn-sm" onclick="deleteData(${e?.id})">Delete</button>
<!--              <button id="myBtn" class="btn btn-danger btn-sm" onclick="myModal(${e?.id})">Delete</button>-->
          </td>
      </tr>`
                    }
                }
            )

            console.log(a);
            table.innerHTML = a;
            const setPagination = () => {
                let a = "";
                const arr = new Array(page_count);
                [...arr].forEach((e, i) => {
                    a += `<li class='page-item ${(i + 1) == page ? "active" : ""}'>
          <a class="page-link" href="?page=${i + 1}">${i + 1}</a>
          </li>`;
                });

                pagination.innerHTML =
                    `<li class="page-item ${page == 1 ? "disabled" : ""} "><a class="page-link" href="?page=${Number(page) - 1}">Previous</a></li>` +
                    a +
                    `<li class="page-item ${page == page_count ? "disabled" : ""} "><a class="page-link" href="?page=${Number(page) + 1}">Next</a></li>`;
            };
            setPagination();
        });
}
getData();

function showAll() {

}

function search() {
    getData();
}

// const openModal = (_id) => {
//     id = _id;
//     console.log("id => ",id);
// };
//
// const closeModal = () => {
//     id = undefined;
//     console.log("id => ",id);
// };


function handleClick(id) {
    window.location.href = `update.html?id=${id}`
}

function deleteData(id) {
    fetch(`http://localhost:8080/book_crud_ee_war_exploded/api/books/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            getData()
        })
}