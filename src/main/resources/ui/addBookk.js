const addBookForm = document.querySelector('form');
const nameInput = document.getElementById('name');
const authorInput = document.getElementById('author');
const descriptionInput = document.getElementById('description');

addBookForm.addEventListener('submit', event => {
    event.preventDefault();
    const book = {
      name: nameInput.value,
      author: authorInput.value,
      description: descriptionInput.value
    };
    fetch('http://localhost:8080/book_crud_ee_war_exploded/api/books', {
      method: 'POST',
      body: JSON.stringify(book),
      headers: {
        'Content-Type': 'application/json',
        'Accept': '*/*'
      }
    })
      .then(response => {
        window.location.href = 'index.html'
      })
  });