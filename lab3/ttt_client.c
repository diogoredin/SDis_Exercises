#include <stdio.h>
#include "ttt_lib.h"
#include "ttt.h"

#define TTT_UNUSED_PLAY_RES 5

void ttt_1( char *host ) {

	CLIENT *clnt;

	char **currentBoard;
	char *currentboard_1_arg;

	int *play_res;
	play_args play_1_arg;

	int *winner;
	char *checkwinner_1_arg;

	char **statistics;
	char *statistics_1_arg;

	/* Player number - 0 or 1 and Square selection number for turn */
	int player = 0;
	int go = 0;

	/* Init Invalid Playres */
	int noPlay = TTT_UNUSED_PLAY_RES;
	play_res = &noPlay;

	/* Create a client */
	clnt = clnt_create(host, TTT, V1, "udp");
	if (clnt == NULL) {
		clnt_pcreateerror(host);
		exit(1);
	}

	/* The main game loop. The game continues for up to 9 turns */
	/* As long as there is no winner */
	do {

		/* Get valid player square selection */
		do {

			/* Print the current board */
			currentBoard = currentboard_1((void*)&currentboard_1_arg, clnt);
			if ( currentBoard == (char **) NULL ) {
				clnt_perror (clnt, "call failed");
			}

			printf("%s\n", *currentBoard);
			printf("\nPlayer %d, please enter the number of the square where you want to place your %c (or 0 to refresh the board): ", player, (player==1) ? 'X' : 'O' );
			scanf("%d", &go);

			if ( go == 0 ) {

				/* Needed because the user didnt use this turn to play */
				play_res = &noPlay;
				continue;

			} else if ( go == -1 ) {

				/* Print the statistics of the game */
				statistics = statistics_1((void*)&statistics_1_arg, clnt);
				if ( statistics == (char **) NULL ) {
					clnt_perror (clnt, "call failed");
				}

				printf("%s\n", *statistics);

				/* Needed because the user didnt use this turn to play */
				play_res = &noPlay;
				continue;

			}

			/* Group the arguments of the play for sending to the server */
			play_1_arg.row = --go/3;                                 
			play_1_arg.column = go%3;
			play_1_arg.player = player;

			/* Make the play */
			play_res = play_1(&play_1_arg, clnt);
			if ( play_res == (int *) NULL ) {
				clnt_perror (clnt, "call failed");
			}

			if (*play_res != 0) {

				switch (*play_res) {
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

		} while(*play_res != 0);

		winner = checkwinner_1((void*)&checkwinner_1_arg, clnt);
		if ( winner == (int *) NULL ) {
			clnt_perror (clnt, "call failed");
		}

		/* Select player */
		player = (player+1)%2;
		printf("player %d\n", player);

	} while ( *winner == -1 );

	/* Game is over so display the final board */
	currentBoard = currentboard_1((void*)&currentboard_1_arg, clnt);
	if ( currentBoard == (char **) NULL ) {
		clnt_perror (clnt, "call failed");
	}

	printf("%s\n", *currentBoard);

	/* Display result message */
	if ( *winner == 2 ) {
		printf("\nHow boring, it is a draw\n");
	} else {
		printf("\nCongratulations, player %d, YOU ARE THE WINNER!\n", *winner);
	}

	clnt_destroy(clnt);
}

int main( int argc, char *argv[] ) {

	char *host;
	if ( argc < 2 ) {
		printf ("usage: %s server_host\n", argv[0]);
		exit(1);
	}

	host = argv[1];
	ttt_1(host);

	exit(0);
}
