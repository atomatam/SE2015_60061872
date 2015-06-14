package kr.ac.mju.util.validator;

import kr.ac.mju.model.GangjwaScore;

import org.springframework.stereotype.Service;

@Service
public class UserScoreValidator {
	public boolean isError(GangjwaScore userScore) {
		if(userScore.getScore().equals("A+") ||
				userScore.getScore().equals("A") ||
				userScore.getScore().equals("A-") ||
				userScore.getScore().equals("B+") ||
				userScore.getScore().equals("B") ||
				userScore.getScore().equals("B-") ||
				userScore.getScore().equals("C+") ||
				userScore.getScore().equals("C") ||
				userScore.getScore().equals("C-") ||
				userScore.getScore().equals("D+") ||
				userScore.getScore().equals("D") ||
				userScore.getScore().equals("D-") ||
				userScore.getScore().equals("F"))
			return false;
		return true;
	}
}
