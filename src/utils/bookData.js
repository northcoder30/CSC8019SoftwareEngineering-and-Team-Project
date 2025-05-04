const books = Array.from({ length: 20 }, (_, i) => ({
    id: i + 1,
    title: `Book Title ${i + 1}`,
    author: `Author ${i + 1}`,
    year: 2000 + (i % 20),
    description: `This is the description of book ${i + 1}.`,
}))

const bookReviews = [
    {
        book_id: 12,
        user_id: 101,
        star_rating: 5,
        review: "Absolutely loved this book! The pacing was perfect and the characters were incredibly well-developed."
    },
    {
        book_id: 27,
        user_id: 102,
        star_rating: 4,
        review: "Great read with an engaging plot. A bit slow at times, but definitely worth finishing."
    },
    {
        book_id: 12,
        user_id: 103,
        star_rating: 3,
        review: "It started strong, but the middle felt repetitive. Still a decent read for the genre."
    },
    {
        book_id: 45,
        user_id: 104,
        star_rating: 2,
        review: "Struggled to stay interested. The story didn’t really go anywhere for me."
    },
    {
        book_id: 8,
        user_id: 105,
        star_rating: 5,
        review: "A masterpiece. Emotional, thought-provoking, and beautifully written from start to finish."
    },
    {
        book_id: 27,
        user_id: 106,
        star_rating: 4,
        review: "Well written and full of twists. Not my favorite ending, but still a solid book."
    },
    {
        book_id: 60,
        user_id: 107,
        star_rating: 1,
        review: "I couldn't connect with the story or the characters. It just wasn't for me."
    },
    {
        book_id: 19,
        user_id: 108,
        star_rating: 5,
        review: "A real page-turner! I read it all in one weekend. Highly recommend it to anyone who loves thrillers."
    },
    {
        book_id: 19,
        user_id: 109,
        star_rating: 4,
        review: "Very enjoyable. There were a few plot holes, but they didn’t ruin the overall experience."
    },
    {
        book_id: 5,
        user_id: 110,
        star_rating: 3,
        review: "Decent book. Not particularly memorable, but it passed the time on a long flight."
    }
];

const BOOK_REVIEWS_KEY = "book-reviews";

/*
The dummy data is inserted above in the local machine to get the behaviour of application when getting the books
but in order to communicate with the back end and the database the below functions need adjusting
 */
export function getBookById(id) {
    return books.find(book => book.id === id) || {}
}

export function getAllBooks() {
    return books
}

export function getAllBookReviews(){
    const list = localStorage.getItem(BOOK_REVIEWS_KEY);
    return list ? JSON.parse(list) : bookReviews;
}

export function getBooksReviews(book){
    const allReviews = getAllBookReviews();
    return allReviews.filter(b => b.book_id === book.id);
}

export function postBookReview(review){
    const allReviews = getAllBookReviews();
    allReviews.push(review);
    localStorage.setItem(BOOK_REVIEWS_KEY, JSON.stringify(allReviews));
}