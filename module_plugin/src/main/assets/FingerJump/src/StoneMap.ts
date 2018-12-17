class StoneMap extends egret.DisplayObjectContainer {
	public constructor() {
		super();
		this.addEventListener(egret.Event.ADDED_TO_STAGE, this.onAddToStage, this);
	}

	private stageW: number;
	private stageH: number;
	private canPassStoneDis: number[] = [];
	private notPassStoneDis: number[] = [];
	private canPassStone: Stone[] = [];
	private notPassStone: Stone[] = [];
	private moveStone: Stone[] = [];

	private onAddToStage() {

		this.removeEventListener(egret.Event.ADDED_TO_STAGE, this.onAddToStage, this);

		this.stageW = this.stage.stageWidth;
		this.stageH = this.stage.stageHeight;

		this.createMap();
	}

	public getCanPassStone(): Stone[] {
		return this.canPassStone;
	}

	public getCanPassStoneDis(): number[] {
		return this.canPassStoneDis;
	}

	private AddCanPassStoneDis() {
		let i: number = Math.round(Math.random() * 1);
		this.canPassStoneDis.push(i);
	}

	private AddNotPassStoneDis() {
		let i: number = Math.round(Math.random() * 10);
		let j: number = 0;
		if (i <= 5) {
			j = 0;
		}
		if (i > 5 && i <= 7) {
			j = 1;
		}
		if (i > 7 && i <= 9) {
			j = 2;
		}
		if (i > 9 && i <= 10) {
			j = 3;
		}
		this.notPassStoneDis.push(j);
	}

	private createMap() {
		let stoneNum: number = 10;
		for (var i = 0; i < stoneNum; i++) {
			this.addStoneAndDis();
		}
	}

	public mapMove() {
		this.removeStoneAndDis();
		//this.addStoneAndDis();
		let stoneW: number = this.canPassStone[0].width;
		let stoneH: number = this.canPassStone[0].height;
		let dis: number = this.canPassStoneDis[0] ? -1 : 1;
		egret.Tween.get(this).to({ x: this.x + dis * (stoneW / 2), y: this.y + (stoneH - 26) }, 200).call(
			function () {
				//this.removeStoneAndDis();
				this.addStoneAndDis();
			}
		);
	}

	private addStoneAndDis() {
		let passStone: Stone = StonePool.getObjectPool(0);
		this.addChildAt(passStone, 0);
		if (this.canPassStone[0] == null) {
			passStone.x = this.stageW - passStone.width >> 1;
			passStone.y = this.stageH - (200 + passStone.height);
			this.canPassStoneDis.push(-1);
		} else {
			this.AddCanPassStoneDis();
			let dis: number = this.canPassStoneDis[this.canPassStoneDis.length - 1] ? 1 : -1;
			passStone.x = this.canPassStone[this.canPassStone.length - 1].x + dis * (passStone.width / 2);
			passStone.y = this.canPassStone[this.canPassStone.length - 1].y - (passStone.height - 26);
		}
		this.canPassStone.push(passStone);

		if (this.canPassStoneDis.length >= 2) {
			this.AddNotPassStoneDis();
			for (var j = 1; j <= this.notPassStoneDis[this.notPassStoneDis.length - 1]; j++) {
				var e: Stone = StonePool.getObjectPool(Math.floor(Math.random() * 4 + 1))//new Stone(Math.floor(Math.random() * 4 + 1));
				this.addChildAt(e, 0);
				let dist: number = this.canPassStoneDis[this.canPassStoneDis.length - 1] ? -1 : 1;
				e.x = this.canPassStone[this.canPassStone.length - 2].x + dist * (passStone.width / 2) * (j);
				e.y = this.canPassStone[this.canPassStone.length - 2].y - (passStone.height - 26) * (j);
				this.notPassStone.push(e);
			}
		}
	}

	private removeStoneAndDis() {
		let stone: Stone = this.canPassStone[0];
		let stoneY: number = stone.y;
		this.canPassStone.splice(0, 1);
		this.canPassStoneDis.splice(0, 1);
		this.notPassStoneDis.splice(0, 1);
		stone.stoneDown();

		for (var i = 0; i < this.notPassStone.length; i++) {
			if (this.notPassStone[i].y >= stoneY) {
				let s: Stone = this.notPassStone[i];
				this.notPassStone.splice(i, 1);
				s.stoneDown();
			}
		}

	}
}