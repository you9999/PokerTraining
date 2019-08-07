package テスト;

import java.util.Arrays;

public class JudgeHandRank {

	/**
	 * @param cardList
	 * @return
	 * 0番目の値：役の強さ<br>
	 * 　ストレートフラッシュ：9<br>
	 * 　フォーカード：8<br>
	 * 　フルハウス：7<br>
	 * 　フラッシュ：6<br>
	 * 　ストレート：5<br>
	 * 　スリーカード：4<br>
	 * 　ツーペア：3<br>
	 * 　ワンペア：2<br>
	 * 　ハイカード：1<br>
	 */
	public int[] judgeHandRank(int[] cardList) throws Exception {
		
		if (cardList.length != 7) {
			throw new Exception("There is not 7 cards.");
		}

		//スート判定/////////////////////////////////////////////////

		boolean flushFlag = false;
		int straightFlushHighCard = 0;
		int flushHighCard = 0;

		int _400Count = 0;
		int _300Count = 0;
		int _200Count = 0;
		int _100Count = 0;

		for (int card : cardList) {

			if (card > 400) {
				_400Count++;
			} else if (card > 300) {
				_300Count++;
			} else if (card > 200) {
				_200Count++;
			} else {
				_100Count++;
			}
		}

		if (_400Count >= 5) {

			flushFlag = true;
			int[] flushCardList = toFlushCardList(cardList, 400);
			straightFlushHighCard = judgeStraight(flushCardList);

			for (int card : flushCardList) {
				flushHighCard = Math.max(flushHighCard, card);
			}
			flushHighCard = flushHighCard % 100;

		} else if (_300Count >= 5) {

			flushFlag = true;
			int[] flushCardList = toFlushCardList(cardList, 300);
			straightFlushHighCard = judgeStraight(flushCardList);

			for (int card : flushCardList) {
				flushHighCard = Math.max(flushHighCard, card);
			}
			flushHighCard = flushHighCard % 100;

		} else if (_200Count >= 5) {

			flushFlag = true;
			int[] flushCardList = toFlushCardList(cardList, 200);
			straightFlushHighCard = judgeStraight(flushCardList);

			for (int card : flushCardList) {
				flushHighCard = Math.max(flushHighCard, card);
			}
			flushHighCard = flushHighCard % 100;

		} else if (_100Count >= 5) {

			flushFlag = true;
			int[] flushCardList = toFlushCardList(cardList, 100);
			straightFlushHighCard = judgeStraight(flushCardList);

			for (int card : flushCardList) {
				flushHighCard = Math.max(flushHighCard, card);
			}
			flushHighCard = flushHighCard % 100;
		}

		if (flushFlag && straightFlushHighCard > 0) {

			// ストレートフラッシュ
			return new int[] { 9, straightFlushHighCard };
		}

		//重複判定/////////////////////////////////////////////////

		// 数字を抽出
		int[] cardNumberList = toCardNumberList(cardList);

		int[][] duplicateList = new int[7][2];
		int i = 0;
		
		for (int cardNumber : cardNumberList) {
			int duplicateNumber = 0;

			for (int targetCardNumber : cardNumberList) {
				if (cardNumber == targetCardNumber) {
					duplicateNumber++;
				}
			}
			
			duplicateList[i] = new int[]  { duplicateNumber, cardNumber };
			i++;
		}

		int fourCardNumber = 0;
		int threeCardCount = 0;
		int pareCount = 0;

		for (int[] duplicate : duplicateList) {

			if (duplicate[0] == 4) {
				fourCardNumber = duplicate[1];
				break;

			} else if (duplicate[0] == 3) {
				threeCardCount++;

			} else if (duplicate[0] == 2) {
				pareCount++;
			}
		}

		if (fourCardNumber > 0) {

			int kicker = 0;

			for (int[] duplicate : duplicateList) {

				if (duplicate[0] <= 3) {
					kicker = Math.max(kicker, duplicate[1]);
				}
			}

			// フォーカード
			return new int[] { 8, fourCardNumber, kicker };
		}

		boolean fullHouseFlag = false;
		int fullHouseFirstNumber = 0;
		int fullHouseSecondNumber = 0;

		// スリーカードが二組ある場合
		if (threeCardCount >= 4) {

			for (int[] duplicate : duplicateList) {

				if (duplicate[0] == 3) {
					if (fullHouseFirstNumber < duplicate[1]) {
						fullHouseSecondNumber = fullHouseFirstNumber;
						fullHouseFirstNumber = duplicate[1];
					} else if (fullHouseFirstNumber > duplicate[1]) {
						fullHouseSecondNumber = Math.max(fullHouseSecondNumber, duplicate[1]);
					}
				}
			}

			fullHouseFlag = true;

			// スリーカードとペアの場合
		} else if (threeCardCount > 0 && pareCount > 0) {

			for (int[] duplicate : duplicateList) {

				if (duplicate[0] == 3) {

					fullHouseFirstNumber = duplicate[1];

				} else if (duplicate[0] == 2) {

					fullHouseSecondNumber = Math.max(fullHouseSecondNumber, duplicate[1]);
				}
			}

			fullHouseFlag = true;
		}

		if (fullHouseFlag) {

			// フルハウス
			return new int[] { 7, fullHouseFirstNumber, fullHouseSecondNumber };
		}

		//フラッシュ以下の判定///////////////////////////////////////////////////////

		if (flushFlag) {

			// フラッシュ
			return new int[] { 6, flushHighCard };
		}

		int straightHighCard = judgeStraight(cardList);

		if (straightHighCard > 0) {

			// ストレート
			return new int[] { 5, straightHighCard };
		}

		if (threeCardCount > 0) {

			int threeCardNumber = 0;
			int firstKicker = 0;
			int secondKicker = 0;

			for (int[] duplicate : duplicateList) {

				if (duplicate[0] == 3) {
					threeCardNumber = duplicate[1];

				} else {
					if (firstKicker < duplicate[1]) {
						secondKicker = firstKicker;
						firstKicker = duplicate[1];
					} else if (firstKicker > duplicate[1]) {
						secondKicker = Math.max(secondKicker, duplicate[1]);
					}
				}
			}

			// スリーカード
			return new int[] { 4, threeCardNumber, firstKicker, secondKicker };
		}

		if (pareCount >= 4) {

			int firstPareNumber = 0;
			int secondPareNumber = 0;
			int kicker = 0;

			for (int[] duplicate : duplicateList) {

				if (duplicate[0] == 2) {
					if (firstPareNumber < duplicate[1]) {
						kicker = Math.max(kicker, secondPareNumber);
						secondPareNumber = firstPareNumber;
						firstPareNumber = duplicate[1];
					} else if (firstPareNumber > duplicate[1]) {
						if (secondPareNumber < duplicate[1]) {
							kicker = Math.max(kicker, secondPareNumber);
							secondPareNumber = duplicate[1];
						} else if (secondPareNumber > duplicate[1]) {
							kicker = Math.max(kicker, duplicate[1]);
						}
					}
				} else {
					kicker = Math.max(kicker, duplicate[1]);
				}
			}

			// ツーペア
			return new int[] { 3, firstPareNumber, secondPareNumber, kicker };
		}

		if (pareCount > 0) {

			int pareNumber = 0;

			for (int[] duplicate : duplicateList) {

				if (duplicate[0] == 2) {
					pareNumber = duplicate[1];
					break;
				}
			}

			Integer[] kickerList = (Integer[]) Arrays.stream(duplicateList)
					.filter(d -> d[0] != 2)
					.map(d -> d[1])
					.toArray(Integer[] :: new);
			
			Arrays.sort(kickerList);

			// ワンペア
			return new int[] { 2,
					pareNumber,
					kickerList[4],
					kickerList[3],
					kickerList[2],
			};
		}

		Arrays.sort(cardNumberList);

		//		System.out.println(Arrays.toString(cardNumberList));

		// ハイカード
		return new int[] { 1,
				cardNumberList[6],
				cardNumberList[5],
				cardNumberList[4],
				cardNumberList[3],
				cardNumberList[2]
		};
	}

	/**
	 * @param cardList
	 * @return
	 *         ストレートである：ハイカードの数字
	 *         ストレートでない：0
	 */
	private int judgeStraight(int[] cardList) {

		int serialCount = 0;

		// 数字を抽出
		int[] cardNumberList = toCardNumberList(cardList);

		// 重複を排除
		int[] noDuplicateCardList = Arrays.stream(cardNumberList).distinct().toArray();

		// A があったら 1 をリストに加える
		boolean existA = false;
		for (int cardNumber : noDuplicateCardList) {

			if (cardNumber == 14) {
				existA = true;
				break;
			}
		}
		if (existA) {
			int[] newList = new int[noDuplicateCardList.length + 1];
			newList[0] = 1;
			System.arraycopy(noDuplicateCardList, 0, newList, 1, noDuplicateCardList.length);
			noDuplicateCardList = newList;
		}

		// 昇順でソート
		Arrays.sort(noDuplicateCardList);

		for (int i = 0; i < noDuplicateCardList.length - 1; i++) {

			if (noDuplicateCardList[i] == noDuplicateCardList[i + 1] - 1) {

				serialCount++;

			} else {

				if (serialCount < 4) {
					serialCount = 0;
				} else {
					return noDuplicateCardList[i];
				}
			}
		}

		if (serialCount < 4) {
			return 0;
		} else {
			return noDuplicateCardList[noDuplicateCardList.length - 1];
		}
	}

	/**
	 * 数字を抽出する
	 * 
	 * @param cardList
	 * @return
	 */
	private int[] toCardNumberList(int[] cardList) {

		return Arrays.stream(cardList)
				.map(c -> c % 100)
				.toArray();
	}

	/**
	 * フラッシュ対象のカードを抽出する
	 * 
	 * @param cardList
	 * @param suit
	 * @return
	 */
	private int[] toFlushCardList(int[] cardList, int suit) {

		return Arrays.stream(cardList)
				.filter(c -> c > suit)
				.toArray();
	}
}
