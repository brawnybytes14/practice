process.stdin.resume();
process.stdin.setEncoding("utf-8");
var stdin_input = "";
 
process.stdin.on("data", function(input) {
    stdin_input += input;
});
 
process.stdin.on("end", function() {
    stdin_input = stdin_input.split(/\n|\s/).map(elem => parseInt(elem));
    main(stdin_input);
});
 
function findClosestSum(arr, n, s) {
    // Write your code here and return the result
    
}
 
function main(input) {
    let t = input.shift();
    while(t--) {
        let n = input.shift();
        let s = input.shift();
        let arr = input.splice(0, n);
        var ans = findClosestSum(arr, n, s);
        console.log(ans);
    }
}