package テスト;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {

		JudgeHandRank judgeHandRank = new JudgeHandRank();
		JudgeWin judgeWin = new JudgeWin();
		RandomHandProcessor rondomHandProcessor = new RandomHandProcessor();

		// プリフロップ ////////////////////////////////////////////////////////////////////////////////////////////
		System.out.println("--preflop--");
		System.out.println();

		int[] myHand = new int[2];
		int[] enemyHand = new int[2];

		rondomHandProcessor.selectPreFlopHand(myHand, enemyHand);

		System.out.println("MyHand:" + cardListToString(myHand));
		System.out.println("EnemyHand:" + cardListToString(enemyHand));
		System.out.println();

		int[][] allPreFlopCombination = rondomHandProcessor.createAllPreFlopCombination();

		int winCount = 0;

		int allCombinationCount = allPreFlopCombination.length;

		//		System.out.println(allCombinationCount);

		for (int i = 0; i < allCombinationCount; i++) {

			int[] myFinalHand = concatIntArray(myHand, allPreFlopCombination[i]);
			int[] enemyFinalHand = concatIntArray(enemyHand, allPreFlopCombination[i]);

			boolean isWin = judgeWin.judgeWin(
					judgeHandRank.judgeHandRank(myFinalHand),
					judgeHandRank.judgeHandRank(enemyFinalHand));

			if (isWin) {
				winCount++;
			}
		}

		double winRate = ((double) winCount / allCombinationCount) * 100;

		System.out.println(winCount + " / " + allCombinationCount);
		System.out.println("WinRate is " + winRate);
		System.out.println();

		if (processBreak()) {
			return;
		}

		// フロップ /////////////////////////////////////////////////////////////////////////////////////////////////
		System.out.println("--flop--");
		System.out.println();

		System.out.println("MyHand:" + cardListToString(myHand));
		System.out.println("EnemyHand:" + cardListToString(enemyHand));
		System.out.println();

		int[] communityCard_flop = rondomHandProcessor.selectFlopCommunityCard();

		System.out.println("CommunityCard:" + cardListToString(communityCard_flop));
		System.out.println();

		int[][] allFlopCombination = rondomHandProcessor.createAllFlopCombination();

		winCount = 0;

		allCombinationCount = allFlopCombination.length;

		for (int i = 0; i < allCombinationCount; i++) {

			int[] myFinalHand = concatIntArray(myHand, communityCard_flop, allFlopCombination[i]);
			int[] enemyFinalHand = concatIntArray(enemyHand, communityCard_flop, allFlopCombination[i]);

			boolean isWin = judgeWin.judgeWin(
					judgeHandRank.judgeHandRank(myFinalHand),
					judgeHandRank.judgeHandRank(enemyFinalHand));

			if (isWin) {
				winCount++;
			}
		}

		winRate = ((double) winCount / allCombinationCount) * 100;

		System.out.println(winCount + " / " + allCombinationCount);
		System.out.println("WinRate is " + winRate);
		System.out.println();

		if (processBreak()) {
			return;
		}

		// ターン ///////////////////////////////////////////////////////////////////////////////////////////////////
		System.out.println("--turn--");
		System.out.println();

		System.out.println("MyHand:" + cardListToString(myHand));
		System.out.println("EnemyHand:" + cardListToString(enemyHand));
		System.out.println();

		int[] communityCard_turn = concatIntArray(communityCard_flop, rondomHandProcessor.selectOneCommunityCard());		

		System.out.println("CommunityCard:" + cardListToString(communityCard_turn));
		System.out.println();

		int[][] allTurnCombination = rondomHandProcessor.createAllTurnCombination();

		winCount = 0;

		allCombinationCount = allTurnCombination.length;

		for (int i = 0; i < allCombinationCount; i++) {

			int[] myFinalHand = concatIntArray(myHand, communityCard_turn, allTurnCombination[i]);
			int[] enemyFinalHand = concatIntArray(enemyHand, communityCard_turn, allTurnCombination[i]);

			boolean isWin = judgeWin.judgeWin(
					judgeHandRank.judgeHandRank(myFinalHand),
					judgeHandRank.judgeHandRank(enemyFinalHand));

			if (isWin) {
				winCount++;
			}
		}

		winRate = ((double) winCount / allCombinationCount) * 100;

		System.out.println(winCount + " / " + allCombinationCount);
		System.out.println("WinRate is " + winRate);
		System.out.println();

		if (processBreak()) {
			return;
		}
		
		// リバー ///////////////////////////////////////////////////////////////////////////////////////////////////
		System.out.println("--river--");
		System.out.println();

		System.out.println("MyHand:" + cardListToString(myHand));
		System.out.println("EnemyHand:" + cardListToString(enemyHand));
		System.out.println();

		int[] communityCard_river = concatIntArray(communityCard_turn, rondomHandProcessor.selectOneCommunityCard());		

		System.out.println("CommunityCard:" + cardListToString(communityCard_river));
		System.out.println();

		winCount = 0;

		int[] myFinalHand = concatIntArray(myHand, communityCard_river);
		int[] enemyFinalHand = concatIntArray(enemyHand, communityCard_river);

		boolean isWin = judgeWin.judgeWin(
				judgeHandRank.judgeHandRank(myFinalHand),
				judgeHandRank.judgeHandRank(enemyFinalHand));

		if (isWin) {
			winCount++;
		}

		winRate = ((double) winCount) * 100;

		System.out.println(winCount + " / " + 1);
		System.out.println("WinRate is " + winRate);
		System.out.println();
	}

	private static String cardListToString(int[] cardList) {

		String[] stringList = new String[cardList.length];

		for (int i = 0; i < cardList.length; i++) {

			stringList[i] = cardToString(cardList[i]);
		}

		return Arrays.toString(stringList);
	}

	private static String cardToString(int card) {

		String string = "";

		if (card > 400) {
			string = "d";
		} else if (card > 300) {
			string = "h";
		} else if (card > 200) {
			string = "c";
		} else {
			string = "s";
		}

		switch (card % 100) {

		case 14:
			return string + "A";
		case 13:
			return string + "K";
		case 12:
			return string + "Q";
		case 11:
			return string + "J";
		case 10:
			return string + "T";
		default:
			return string + (card % 100);
		}
	}

	private static int[] concatIntArray(int[] firstArray, int[] secondArray) {

		int length = firstArray.length + secondArray.length;

		int[] newArray = new int[length];

		System.arraycopy(firstArray, 0, newArray, 0, firstArray.length);
		System.arraycopy(secondArray, 0, newArray, firstArray.length, secondArray.length);

		return newArray;
	}

	private static int[] concatIntArray(int[] firstArray, int[] secondArray, int[] thirdArray) {

		int length = firstArray.length + secondArray.length + thirdArray.length;

		int[] newArray = new int[length];

		System.arraycopy(firstArray, 0, newArray, 0, firstArray.length);
		System.arraycopy(secondArray, 0, newArray, firstArray.length, secondArray.length);
		System.arraycopy(thirdArray, 0, newArray, firstArray.length + secondArray.length, thirdArray.length);

		return newArray;
	}

	private static boolean processBreak() {

		System.out.println("キーを押してください。");
		System.out.println("終了：q");
		System.out.println("続行：それ以外");

		Scanner scanner = new Scanner(System.in);

		String userInput = scanner.nextLine();

		if (userInput.equals("q")) {
			return true;
		} else {
			return false;
		}
	}
}
