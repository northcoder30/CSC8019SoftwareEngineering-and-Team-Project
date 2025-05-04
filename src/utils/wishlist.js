const WISHLIST_KEY = 'ebook-wishlist'

/*
The methods below currently store the objects in the localstorage but needs to be send to the backend and the database
by using something like an API
 */

export function getWishlist() {
    const list = localStorage.getItem(WISHLIST_KEY)
    return list ? JSON.parse(list) : []
}

export function addToWishlist(book) {
    const list = getWishlist()
    const exists = list.some(item => item.id === book.id)
    if (!exists) {
        list.push(book)
        localStorage.setItem(WISHLIST_KEY, JSON.stringify(list))
    }
}

export function removeFromWishlist(id) {
    let list = getWishlist()
    list = list.filter(book => book.id !== id)
    localStorage.setItem(WISHLIST_KEY, JSON.stringify(list))
}
