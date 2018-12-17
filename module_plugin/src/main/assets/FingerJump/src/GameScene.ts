class GameScene extends egret.DisplayObjectContainer {
	public constructor() {
		super();
		this.addEventListener(egret.Event.ADDED_TO_STAGE, this.onAddToStage, this);
	}

	private stageW: number;
	private stageH: number;
	private player: Player;
	private leaf: leaf;
	private map: StoneMap;
	private canPassStoneDis: number[];
	private canPassStone: Stone[];
	private stoneDownTimer: egret.Timer;

	private onAddToStage() {

		this.removeEventListener(egret.Event.ADDED_TO_STAGE, this.onAddToStage, this);

		this.stageW = this.stage.stageWidth;
		this.stageH = this.stage.stageHeight;

		let bgr: bg = new bg();
		this.addChild(bgr);

		let mapr: StoneMap = new StoneMap();
		this.map = mapr;
		this.addChild(this.map);

		let leafr: leaf = new leaf();
		this.leaf = leafr;
		this.addChild(this.leaf);

		let palyer: Player = new Player();
		this.player = palyer;
		this.addChild(this.player);
		this.player.x = this.stageW / 2;
		this.player.y = this.map.getCanPassStone()[0].y;
		this.player.playerDirection(this.map.getCanPassStoneDis()[1]);

		this.addEventListener(egret.TouchEvent.TOUCH_TAP, this.playerJump, this);
		this.touchEnabled = true;

		this.stoneDownTimer = new egret.Timer(1500, 1);
		this.stoneDownTimer.addEventListener(egret.TimerEvent.TIMER, this.timeOver, this);
	}

	private playerJump(e: egret.TouchEvent) {
		if (this.stoneDownTimer.running) {
			this.stoneDownTimer.stop();
		}
		this.canPassStoneDis = this.map.getCanPassStoneDis();
		this.canPassStone = this.map.getCanPassStone();
		let downStone: Stone = this.canPassStone[0];
		let touchX: number = e.stageX;

		if (touchX <= this.stageW / 2) {
			this.player.playerJump(0);
			if (this.canPassStoneDis[1] == 0) {
				this.jumpRight();
			} else {
				downStone.stoneDown();
				this.jumpDied();
			}
		}
		if (touchX > this.stageW / 2) {
			this.player.playerJump(1);
			if (this.canPassStoneDis[1] == 1) {
				this.jumpRight();
			} else {
				downStone.stoneDown();
				this.jumpDied();
			}
		}
	}

	private jumpRight(){
		this.map.mapMove();
		this.leaf.move();
		this.stoneDownTimer.start();
	}

	private jumpDied() {
		this.removeEventListener(egret.TouchEvent.TOUCH_TAP, this.playerJump, this);
		this.player.playerDied(0);
		console.log("player died!");
	}

	private timeOver(e: egret.TimerEvent) {
		this.removeEventListener(egret.TouchEvent.TOUCH_TAP, this.playerJump, this);
		this.stoneDownTimer.removeEventListener(egret.TimerEvent.TIMER, this.timeOver, this);
		console.log("time is over !");
		
		this.canPassStone = this.map.getCanPassStone();
		let downStone: Stone = this.canPassStone[0];
		downStone.stoneDown();
		this.player.playerDied(1);
	}

}