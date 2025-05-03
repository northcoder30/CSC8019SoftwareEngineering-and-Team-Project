import {getAllBooks} from "@/utils/bookData";

const PURCHASED_KEY = 'ebook-purchased';

// TODO - This should probably accept an argument for the user, so the back end can query and return the purchased books for the particular user only.
export function getPurchasedBooks() {
    const list = localStorage.getItem(PURCHASED_KEY);
    return list ? JSON.parse(list) : [];
}

// TODO - Same as above - Should probably accept an arg for the user
export function addPurchasedBook(book) {
    const list = getPurchasedBooks();
    const exists = list.some(item => item.id === book.id);
    if (!exists) {
        list.push(book);
        localStorage.setItem(PURCHASED_KEY, JSON.stringify(list));
    }
}

export function cancelPurchasedBook(book){
    const books = getPurchasedBooks();
    const newBooks = books.filter(b => b.id !== book.id);
    localStorage.setItem(PURCHASED_KEY, JSON.stringify(newBooks));

}