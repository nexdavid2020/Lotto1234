package lottoguhagi;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Lotto_version_02 {
	
	public static void main(String[] args) {
		// 당신의 인생에서 로또 1등이 정말 가능할까요?
		
		
		Scanner scan = new Scanner(System.in);
		System.out.println("당신의 인생에서 로또 1등이 정말 가능할까요??");
		System.out.print("당신의 이름: ");
		String name = scan.nextLine();
		
		
		boolean bool = true;
		int gameSu = 0;
		
		while(bool) {
			System.out.print("매주 몇 게임씩 하시겠습니까? ");
			gameSu = scan.nextInt();
			long a = gameSu * 1000L;
			System.out.printf("매주 %d원씩 쓰시는 것입니다. %n", a);
			System.out.println("맞습니까?");
			System.out.println("1.맞다\n2.잘못 입력했다");
			int num = scan.nextInt();

			if (num == 1) {
				break;
			} else if (num == 2) {
				continue;
			} else {
				System.out.println("잘못 입력하셨습니다.");
				System.out.println("다시 입력하세요.");
				continue;
			}

		}
		
		// Scanner 닫아주기
		scan.close();

		// 정답 배열
		int[] answer = new int[7];
		int answer_bonus;
		
		// 내 게임 배열
		// MyGames[i][6] == 보너스 번호
		int[][] MyGames = new int[gameSu][7];
		
	
		// 1등 ~ 5등 카운트
		
		int count_1=0;
		int count_2=0;
		int count_3=0;
		int count_4=0;
		int count_5=0;
		
		
		
		int trial=0;

		
		while(true) {  // 무한루프 시작
			
			

			// answer 배열에 1 ~ 45까지의 임의의 정수를 중복 수 없이 받기

			for(int i = 0; i < answer.length; i++) {
				answer[i] = (int) (Math.random() * 45) + 1;

				for (int j = 0; j < i; j++) {
					if (answer[j] == answer[i]) {
						i--;
						break;
					}
				}
			}
			
			// answer배열의 7번째 숫자를 보너스 번호로 지정
			answer_bonus = answer[6];
		
			
			
			// answer 배열 오름차순으로 정렬
			
			for(int i=0; i<6; i++) {
				int temp;
				for(int j=i; j<6; j++) {
					if(answer[i]>answer[j]) {
						temp = answer[i];
						answer[i] = answer[j];
						answer[j] = temp;
					}
				}
			}
			
			
			
			// 이제 내 게임에 로또 번호 채우기
			// int[][] MyGames = new int[gameSu][7];
			// 중복수 다시 받기
			
			for(int k = 0; k < gameSu; k++) {
				
				for(int i = 0; i < 7; i++) {
					MyGames[k][i] = (int) (Math.random() * 45) + 1;
					
					for(int j = 0; j < i; j++) {
						if(MyGames[k][i] == MyGames[k][j]) {
							i--;
							break;
						}
					}
				}
			}
			
			// MyGames 오름차순으로 정리
			
			for(int k = 0; k < gameSu; k++) {
			
				for(int i = 0; i < 6; i++) {
					int temp;
					for(int j = i; j < 6; j++) {
						if(MyGames[k][i] > MyGames[k][j]) {
							temp = MyGames[k][i];
							MyGames[k][i] = MyGames[k][j];
							MyGames[k][j] = temp;
						}
					}
				}
			}
			
			
			//카운트
			int count;
			for(int k = 0; k < gameSu; k++) {
				count = 0;
				
				for(int i=0; i < 6; i++) {
					
					for(int j = 0; j < 6; j++) {
						if(answer[i] == MyGames[k][j]) {
							count++;
						}
					}
				}
				
				if(count==6) {
					count_1++;
				}else if(count==5 && (MyGames[k][6] == answer_bonus)) {
					count_2++;
				}else if(count==5 && (MyGames[k][6] != answer_bonus)) {
					count_3++;
				}else if(count==4) {
					count_4++;
				}else if(count==3) {
					count_5++;
				}
				
			}
			
			
			
			trial++;
			System.out.println("시행횟수 : " + trial);
			
			if(count_1 >= 1) { // 1등 당첨이 처음 나왔을 때 무한루프 탈출
				System.out.println("정답: " + Arrays.toString(answer));
				System.out.println("<나의 정답>");
				for(int k=0; k < MyGames.length; k++) {
					System.out.println(Arrays.toString(MyGames[k]));
				}
				break;
			}
		
		} // while문 끝
		
		long money = trial * gameSu * 1000L;
		
		System.out.println();
		System.out.println(name + "님의 인생에서 로또 1등을 경험하려면..");
		System.out.printf("매주마다 %d게임(%d원 씩)의 로또를 산다는 가정하에 \n", gameSu, gameSu*1000L);
		System.out.println("*** 1등 당첨이 될 때 까지 시행한 결과 ***");
		System.out.println("총 시행 횟수: " + trial);
		System.out.printf("총 %.0f년 걸립니다... \n", (double)trial / 52);
		System.out.printf("로또 구매 비용은 총 %d원 입니다. \n", money);
		System.out.println("< 1등 당첨될 때 까지 2 ~ 5등 경험 횟수 >");
		System.out.println("2등: " + count_2);
		System.out.println("3등: " + count_3);
		System.out.println("4등: " + count_4);
		System.out.println("5등: " + count_5);
		
	}
	
}
