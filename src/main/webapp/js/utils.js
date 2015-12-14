/**
 * Return localeSeparator
 * @returns {number}
 */
function whatDecimalSeparator() {
    var n = 1.1;
    n = n.toLocaleString().substring(1, 2);
    return n;
}
