package stringalgos

class Trie() {

    private val root: TrieNode = TrieNode()

    fun insert(word: String) {
        var node = root
        word.forEach {
            if (node.contains(it).not()) {
                node.put(it, TrieNode())
            }
            node = node.get(it)
        }
        node.setIsEnd()
    }

    fun search(word: String): Boolean {
        var node = root
        word.forEach {
            if (node.contains(it).not()) {
                return false
            }
            node = node.get(it)
        }
        return node.isEnd()
    }

    fun startsWith(prefix: String): Boolean {
        var node = root
        prefix.forEach {
            if (node.contains(it).not()) {
                return false
            }
            node = node.get(it)
        }
        return true
    }

}

class TrieNode {
    private var isEnd = false
    private val links = Array<TrieNode?>(26) { null }

    fun put(character: Char, node: TrieNode) {
        links[character - 'a'] = node
    }

    fun contains(letter: Char): Boolean {
        return links[letter - 'a'] != null
    }

    fun isEnd(): Boolean = isEnd

    fun setIsEnd() {
        isEnd = true
    }

    fun get(character: Char): TrieNode {
        return links[character - 'a']!!
    }
}