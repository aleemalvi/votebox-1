/**
  * This file is part of VoteBox.
  * 
  * VoteBox is free software: you can redistribute it and/or modify
  * it under the terms of the GNU General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  * 
  * VoteBox is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU General Public License for more details.
  * 
  * You should have received a copy of the GNU General Public License
  * along with VoteBox.  If not, see <http://www.gnu.org/licenses/>.
 */
package actionparser;

import sexpression.ASExpression;
import sexpression.ListExpression;

public class UIAction {
	
	private String _UID;
	private String _type;
	private String _action;
	private long _time;

	public UIAction(ASExpression sexp) {
		ListExpression list=null;
		try{
			list=(ListExpression)sexp;
		}catch(ClassCastException e){
			System.out.println("Invalid UIAction S-Expression");
			System.exit(1);
		}
		
		//TODO more validation
		String temp=list.get(0).toString();
		_UID=temp.substring(1,temp.length()-1);
		
		temp=list.get(1).toString();
		_type=temp.substring(1,temp.length()-1);
		
		temp=list.get(2).toString();
		_action=temp.substring(1,temp.length()-1);
		
		temp=list.get(3).toString();
		_time=Long.parseLong(temp.substring(1,temp.length()-1));
		
	}
	
	public String toString(){
		return "UID: " +_UID + " Type: " + _type + " Action: " + _action + " Time: " + _time; 
	}

	/**
	 * @return Returns the _action.
	 */
	public String get_action() {
		return _action;
	}

	/**
	 * @param _action The _action to set.
	 */
	public void set_action(String _action) {
		this._action = _action;
	}

	/**
	 * @return Returns the _time.
	 */
	public long get_time() {
		return _time;
	}

	/**
	 * @param _time The _time to set.
	 */
	public void set_time(long _time) {
		this._time = _time;
	}

	/**
	 * @return Returns the _type.
	 */
	public String get_type() {
		return _type;
	}

	/**
	 * @param _type The _type to set.
	 */
	public void set_type(String _type) {
		this._type = _type;
	}

	/**
	 * @return Returns the _UID.
	 */
	public String get_UID() {
		return _UID;
	}

	/**
	 * @param _uid The _UID to set.
	 */
	public void set_UID(String _uid) {
		_UID = _uid;
	}

}
