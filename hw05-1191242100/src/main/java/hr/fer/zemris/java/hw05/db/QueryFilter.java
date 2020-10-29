/**
 * 
 */
package hr.fer.zemris.java.hw05.db;

import java.util.List;

/**
 * Class that implements interface {@link IFilter}.
 */
public class QueryFilter implements IFilter {

	/**
	 * List of queries
	 */
	private List<ConditionalExpression> queryList;
	
	/**
	 * Constructor
	 * @param queries list of conditional expressions
	 */
	public QueryFilter(List<ConditionalExpression> queries) {
		queryList = queries;
	}

	@Override
	public boolean accepts(StudentRecord record) {
		
		for(ConditionalExpression expression : queryList) {
			if(expression.getFieldGetter() == FieldValueGetters.JMBAG) {
				if(!expression.getComparisonOperator().satisfied(record.getJmbag(), expression.getStringLiteral())) {
					return false;
				}
			} else if(expression.getFieldGetter() == FieldValueGetters.FIRST_NAME) {
				if(!expression.getComparisonOperator().satisfied(record.getFirstName(), expression.getStringLiteral())) {
					return false;
				}
			} else if(expression.getFieldGetter() == FieldValueGetters.LAST_NAME) {
				if(!expression.getComparisonOperator().satisfied(record.getLastName(), expression.getStringLiteral())) {
					return false;
				}
			} else {
				throw new IllegalArgumentException("I do not support your field getter.");
			}
		}
		return true;
	}
}
