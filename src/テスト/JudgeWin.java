package テスト;

public class JudgeWin {

	public boolean judgeWin(int[] myHand, int[]... otherHandList) {

		for (int[] otherHand : otherHandList) {

			if (myHand[0] < otherHand[0]) {
				return false;
			}
		}

		for (int[] otherHand : otherHandList) {

			if (myHand[0] == otherHand[0]) {

				for (int i = 1; i < myHand.length - 1; i++) {

					if (myHand[i] < otherHand[i]) {
						return false;
					}
				}
			}
		}
		return true;
	}
}
