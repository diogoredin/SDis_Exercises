#include <stdio.h>
#include "ttt_lib.h"

#define TTT_UNUSED_PLAY_RES 5

int main() {

	/* Player number - 0 or 1 */
	int player = 0;

	/* Square selection number for turn */
	int go = 0;

	/* Row index for a square */
	int row = 0;

	/* Column index for a square */
	int column = 0;

	/* The winning player */
	int winner = -1;

	int play_res;
	char buffer[MAX_BUFFER_LEN];

	/* The main game loop. The game continues for up to 9 turns */
	/* As long as there is no winner */
	do {

		/* Get valid player square selection */
		do {

			/* Print current board */
			currentBoard(buffer);
			printf("%s\n", buffer);

			printf("\nPlayer %d, please enter the number of the square where you want to place your %c (or 0 to refresh the board): ", player, (player==1) ? 'X' : 'O' );
			scanf(" %d", &go);

			if ( go == 0 ) {
				play_res = TTT_UNUSED_PLAY_RES;
				continue;
			}

			/* Get row index and column index of the square */
			row = --go/3;
			column = go%3;

			play_res = play(row, column, player);

			if (play_res != 0) {

				switch (play_res) {
				case 1:
					printf("Position outside board.");
					break;
				case 2:
					printf("Square already taken.");
					break;
				case 3:
					printf("Not your turn.");
					break;
				case 4:
					printf("Game has finished.");
					break;
				}

				printf(" Try again...\n");
			}

		} while(play_res != 0);

		winner = checkWinner();

		/* Select player */
		player = (player+1)%2;

		printf("player %d\n", player);

	} while (winner == -1);

	/* Game is over so display the final board */
	currentBoard(buffer);
	printf("%s\n", buffer);

	/* Display result message */
	if ( winner == 2 ) {
		printf("\nHow boring, it is a draw\n");
	} else {
		printf("\nCongratulations, player %d, YOU ARE THE WINNER!\n", winner);
	}

	return 0;
}

