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
package votebox.events;

import java.util.HashMap;

import sexpression.*;

/**
 * Event that represents the cast-ballot message:<br>
 * 
 * <pre>
 * (cast-ballot nonce encrypted-ballot)
 * </pre>
 * 
 * See <a href="https://sys.cs.rice.edu/votebox/trac/wiki/VotingMessages">
 * https://sys.cs.rice.edu/votebox/trac/wiki/VotingMessages</a> for a complete
 * list of messages.
 * 
 * @author cshaw
 */
public class CastBallotEvent implements IAnnounceEvent {

    private int _serial;
    private ASExpression _nonce;
    private ASExpression _ballot;

    /**
     * Matcher for the CastBallotEvent
     */
    private static MatcherRule MATCHER = new MatcherRule() {
        private ASExpression pattern = ASExpression
                .make("(cast-ballot %nonce:#string %ballot:#any)");

        public IAnnounceEvent match(int serial, ASExpression sexp) {
            HashMap<String, ASExpression> result = pattern.namedMatch(sexp);
            if (result != NamedNoMatch.SINGLETON)
                return new CastBallotEvent(serial, result.get("nonce"), result
                        .get("ballot"));

            return null;
        };
    };

    /**
     * 
     * @return a MatcherRule for parsing this event type.
     */
    public static MatcherRule getMatcher(){
    	return MATCHER;
    }//getMatcher
    
    /**
     * Constructs a new CastBallotEvent
     * 
     * @param serial
     *            the serial number of the sender
     * @param nonce
     *            the nonce
     * @param ballot
     *            the encrypted ballot, as an array of bytes
     */
    public CastBallotEvent(int serial, ASExpression nonce, ASExpression ballot) {
        _serial = serial;
        _nonce = nonce;
        _ballot = ballot;
    }

    /**
     * @return the ballot
     */
    public ASExpression getBallot() {
        return _ballot;
    }

    /**
     * @return the nonce
     */
    public ASExpression getNonce() {
        return _nonce;
    }

    public int getSerial() {
        return _serial;
    }

    /**
     * @see votebox.events.IAnnounceEvent#fire(votebox.events.VoteBoxEventListener)
     */
    public void fire(VoteBoxEventListener l) {
        l.castBallot(this);
    }

    /**
     * @see votebox.events.IAnnounceEvent#toSExp()
     */
    public ASExpression toSExp() {
        return new ListExpression(StringExpression.makeString("cast-ballot"),
                _nonce, _ballot);
    }
}
