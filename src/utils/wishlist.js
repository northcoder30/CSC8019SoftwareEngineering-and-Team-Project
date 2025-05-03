const WISHLIST_KEY = 'ebook-wishlist'

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
