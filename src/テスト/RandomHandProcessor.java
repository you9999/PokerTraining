package テスト;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomHandProcessor {

	private Random random = new Random();;

	public List<Integer> remainningCardList = createAllCardList();

	public void selectPreFlopHand(int[] myHand, int[] enemyHand) {

		int index = 0;

		index = random.nextInt(remainningCardList.size());
		myHand[0] = remainningCardList.remove(index);

		index = random.nextInt(remainningCardList.size());
		myHand[1] = remainningCardList.remove(index);

		index = random.nextInt(remainningCardList.size());
		enemyHand[0] = remainningCardList.remove(index);

		index = random.nextInt(remainningCardList.size());
		enemyHand[1] = remainningCardList.remove(index);
	}

	public int[][] createAllPreFlopCombination() {

		int[][] resultList = new int[1712304][5];

		int allCardCount = remainningCardList.size();

		int totalCount = 0;

		int[] combination = new int[5];

		for (int i = 0; i <= allCardCount - 5; i++) {

			int firstCard = remainningCardList.get(i);
			combination[0] = firstCard;

			for (int j = i + 1; j <= allCardCount - 4; j++) {

				int secondCard = remainningCardList.get(j);
				combination[1] = secondCard;

				for (int k = j + 1; k <= allCardCount - 3; k++) {

					int thirdCard = remainningCardList.get(k);
					combination[2] = thirdCard;

					for (int l = k + 1; l <= allCardCount - 2; l++) {

						int fourthCard = remainningCardList.get(l);
						combination[3] = fourthCard;

						for (int m = l + 1; m <= allCardCount - 1; m++) {

							int fifthCard = remainningCardList.get(m);
							combination[4] = fifthCard;

							int[] resultCombination = new int[5];
							System.arraycopy(combination, 0, resultCombination, 0, 5);

							resultList[totalCount] = resultCombination;

							totalCount++;
						}
					}
				}
			}
		}
		return resultList;
	}
	
	public int[] selectFlopCommunityCard() {

		int[] communityCard_flop = new int[3];

		int index = 0;

		index = random.nextInt(remainningCardList.size());
		communityCard_flop[0] = remainningCardList.remove(index);

		index = random.nextInt(remainningCardList.size());
		communityCard_flop[1] = remainningCardList.remove(index);

		index = random.nextInt(remainningCardList.size());
		communityCard_flop[2] = remainningCardList.remove(index);
		
		return communityCard_flop;
	}
	
	public int[][] createAllFlopCombination() {
		
		int[][] resultList = new int[990][2];

		int allCardCount = remainningCardList.size();

		int totalCount = 0;

		int[] combination = new int[2];

		for (int i = 0; i <= allCardCount - 2; i++) {

			int firstCard = remainningCardList.get(i);
			combination[0] = firstCard;

			for (int j = i + 1; j <= allCardCount - 1; j++) {

				int secondCard = remainningCardList.get(j);
				combination[1] = secondCard;

				int[] resultCombination = new int[2];
				System.arraycopy(combination, 0, resultCombination, 0, 2);

				resultList[totalCount] = resultCombination;

				totalCount++;
			}
		}
		return resultList;

	}

	public int[] selectOneCommunityCard() {

		int[] newCommunityCard = new int[1];

		int index = random.nextInt(remainningCardList.size());
		newCommunityCard[0] = remainningCardList.remove(index);

		return newCommunityCard;
	}

	public int[][] createAllTurnCombination() {

		int[][] resultList = new int[44][1];

		int allCardCount = remainningCardList.size();

		int totalCount = 0;

		int[] combination = new int[1];

		for (int i = 0; i <= allCardCount - 1; i++) {

			int firstCard = remainningCardList.get(i);
			combination[0] = firstCard;

			int[] resultCombination = new int[1];
			System.arraycopy(combination, 0, resultCombination, 0, 1);

			resultList[totalCount] = resultCombination;

			totalCount++;
		}
		return resultList;
	}

	private static List<Integer> createAllCardList() {

		List<Integer> allCardList = new ArrayList<>();

		allCardList.add(102);
		allCardList.add(103);
		allCardList.add(104);
		allCardList.add(105);
		allCardList.add(106);
		allCardList.add(107);
		allCardList.add(108);
		allCardList.add(109);
		allCardList.add(110);
		allCardList.add(111);
		allCardList.add(112);
		allCardList.add(113);
		allCardList.add(114);
		allCardList.add(202);
		allCardList.add(203);
		allCardList.add(204);
		allCardList.add(205);
		allCardList.add(206);
		allCardList.add(207);
		allCardList.add(208);
		allCardList.add(209);
		allCardList.add(210);
		allCardList.add(211);
		allCardList.add(212);
		allCardList.add(213);
		allCardList.add(214);
		allCardList.add(302);
		allCardList.add(303);
		allCardList.add(304);
		allCardList.add(305);
		allCardList.add(306);
		allCardList.add(307);
		allCardList.add(308);
		allCardList.add(309);
		allCardList.add(310);
		allCardList.add(311);
		allCardList.add(312);
		allCardList.add(313);
		allCardList.add(314);
		allCardList.add(402);
		allCardList.add(403);
		allCardList.add(404);
		allCardList.add(405);
		allCardList.add(406);
		allCardList.add(407);
		allCardList.add(408);
		allCardList.add(409);
		allCardList.add(410);
		allCardList.add(411);
		allCardList.add(412);
		allCardList.add(413);
		allCardList.add(414);

		return allCardList;
	}

}
