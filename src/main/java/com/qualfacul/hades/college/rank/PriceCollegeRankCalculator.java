package com.qualfacul.hades.college.rank;

import static com.qualfacul.hades.college.CollegeGradeOrigin.STUDENT_PRICE;

import java.util.List;
import java.util.stream.Collectors;

import com.qualfacul.hades.college.CollegeGrade;
import com.qualfacul.hades.college.CollegeGradeCalculator;

class PriceCollegeRankCalculator implements CollegeRankCalculator {

	@Override
	public RankCalculatorResult calculate(List<CollegeGrade> gradesToFilter) {
		List<CollegeGrade> grades = gradesToFilter.stream()
				.filter(grade -> STUDENT_PRICE.equals(grade.getGradeOrigin()))
				.collect(Collectors.toList());
			
		return new CollegeGradeCalculator().calculate(grades);
	}

}
