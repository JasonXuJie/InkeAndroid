class StonePool {
	private static canPassStonePool: Stone[] = [];
	private static notPassStonePool: Stone[] = [];

		public static addObjectPool(stone: Stone) {
		if (stone.name === "0") {
			this.canPassStonePool.push(stone);
		} else {
			this.notPassStonePool.push(stone);
		}
	}

	public static getObjectPool(stoneNum: number): Stone {
		if (stoneNum.toString() === "0") {
			if (this.canPassStonePool.length >= 2) {
				return this.canPassStonePool.shift();
			} else {
				return new Stone(0);
			}
		} else {
			if (this.notPassStonePool.length) {
				for (var i = 0; i < this.notPassStonePool.length; i++) {
					let e = this.notPassStonePool[i];
					if (e.name === stoneNum.toString()) {
						this.notPassStonePool.splice(i, 1);
						return e;
					} else {
						return new Stone(stoneNum);
					}
				}
			} else {
				return new Stone(stoneNum);
			}
		}
	}

	public static removeObjectPool() {
		this.canPassStonePool = [];
		this.notPassStonePool = [];
	}
}