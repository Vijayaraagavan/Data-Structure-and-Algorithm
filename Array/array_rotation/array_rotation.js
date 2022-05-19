export function array_rot(arr, d, n) {
    const res = arr.slice(d, n).concat(arr.slice(0, d));
    return res
  }
// module.exports = array_rot