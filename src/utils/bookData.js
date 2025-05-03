const books = Array.from({ length: 20 }, (_, i) => ({
    id: i + 1,
    title: `Book Title ${i + 1}`,
    author: `Author ${i + 1}`,
    year: 2000 + (i % 20),
    description: `This is the description of book ${i + 1}.`,
}))

export function getBookById(id) {
    return books.find(book => book.id === id) || {}
}

export function getAllBooks() {
    return books
}