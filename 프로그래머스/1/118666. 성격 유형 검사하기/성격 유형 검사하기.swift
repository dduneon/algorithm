import Foundation

func solution(_ survey:[String], _ choices:[Int]) -> String {
    var isReverse: Bool = false
    var MBTI: [Int] = [0, 0, 0, 0]
    let score: [Int] = [3, 2, 1, 0, -1, -2, -3]
    let revscore: [Int] = [-3, -2, -1, 0, 1, 2, 3]
    // RT CF JM AN
    var index: Int = 0
    var i: Int = 0
    
    for sur in survey {
        switch(sur) {
        case "RT":
            index = 0
            isReverse = false
        case "CF":
            index = 1
            isReverse = false
        case "JM":
            index = 2
            isReverse = false
        case "AN":
            index = 3
            isReverse = false
            
        case "TR":
            index = 0
            isReverse = true
        case "FC":
            index = 1
            isReverse = true
        case "MJ":
            index = 2
            isReverse = true
        case "NA":
            index = 3
            isReverse = true
            
        default:
            break
        }
        if(isReverse) {
            MBTI[index] += revscore[choices[i]-1]
        } else {
            MBTI[index] += score[choices[i]-1]
        }
        i += 1
    }
    
    var str: String = ""
    if(MBTI[0] >= 0) {
        str.append(contentsOf: "R")
    } else { str.append(contentsOf: "T") }
    
    if(MBTI[1] >= 0) {
        str.append(contentsOf: "C")
    } else { str.append(contentsOf: "F") }
    
    if(MBTI[2] >= 0) {
        str.append(contentsOf: "J")
    } else { str.append(contentsOf: "M") }
    
    if(MBTI[3] >= 0) {
        str.append(contentsOf: "A")
    } else { str.append(contentsOf: "N") }
    
    return str
}
