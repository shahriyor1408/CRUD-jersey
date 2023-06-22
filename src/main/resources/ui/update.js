const searchParams = new URLSearchParams(window.location.search);

  const id = searchParams.get("id");

  const updateBookForm = document.querySelector('form');
  const nameInput = document.getElementById('name');
  const authorInput = document.getElementById('author');
  const descriptionInput = document.getElementById('description');

  const getdata = () => {
      fetch(`http://localhost:8080/book_crud_ee_war_exploded/api/books/${id}`, {
      method: 'GET',
      headers: {
          'Content-Type': 'application/json'
      }
      })
      .then(response => response.json())
    .then(book => {
        nameInput.value = book?.name
        authorInput.value = book?.author
        descriptionInput.value = book?.description
    });


    const book = {
    id,
    name: nameInput.value,
    author: authorInput.value,
    description: descriptionInput.value
    };
};

getdata();

updateBookForm.addEventListener('submit', event => {
    event.preventDefault();
    const book = {
    id,
    name: nameInput.value,
    author: authorInput.value,
    description: descriptionInput.value
    };
    fetch(`http://localhost:8080/book_crud_ee_war_exploded/api/books`, {
    method: 'PUT',
    body: JSON.stringify(book),
    headers: {
        'Content-Type': 'application/json'
    }
    })
    .then(response => {
        window.location.href = ("index.html")
    })
});