var __reflect = (this && this.__reflect) || function (p, c, t) {
    p.__class__ = c, t ? t.push(c) : t = [c], p.__types__ = p.__types__ ? t.concat(p.__types__) : t;
};
var StonePool = (function () {
    function StonePool() {
    }
    StonePool.addObjectPool = function (stone) {
        if (stone.name === "0") {
            this.canPassStonePool.push(stone);
        }
        else {
            this.notPassStonePool.push(stone);
        }
    };
    StonePool.getObjectPool = function (stoneNum) {
        if (stoneNum.toString() === "0") {
            if (this.canPassStonePool.length >= 2) {
                return this.canPassStonePool.shift();
            }
            else {
                return new Stone(0);
            }
        }
        else {
            if (this.notPassStonePool.length) {
                for (var i = 0; i < this.notPassStonePool.length; i++) {
                    var e = this.notPassStonePool[i];
                    if (e.name === stoneNum.toString()) {
                        this.notPassStonePool.splice(i, 1);
                        return e;
                    }
                    else {
                        return new Stone(stoneNum);
                    }
                }
            }
            else {
                return new Stone(stoneNum);
            }
        }
    };
    StonePool.removeObjectPool = function () {
        this.canPassStonePool = [];
        this.notPassStonePool = [];
    };
    StonePool.canPassStonePool = [];
    StonePool.notPassStonePool = [];
    return StonePool;
}());
__reflect(StonePool.prototype, "StonePool");
//# sourceMappingURL=StonePool.js.map