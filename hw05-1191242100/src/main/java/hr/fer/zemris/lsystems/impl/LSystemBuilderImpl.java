/**
 * 
 */
package hr.fer.zemris.lsystems.impl;

import java.awt.Color;
import java.util.Objects;

import hr.fer.zemris.java.custom.collections.Dictionary;
import hr.fer.zemris.lsystems.LSystem;
import hr.fer.zemris.lsystems.LSystemBuilder;
import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.commands.ColorCommand;
import hr.fer.zemris.lsystems.impl.commands.DrawCommand;
import hr.fer.zemris.lsystems.impl.commands.PopCommand;
import hr.fer.zemris.lsystems.impl.commands.PushCommand;
import hr.fer.zemris.lsystems.impl.commands.RotateCommand;
import hr.fer.zemris.lsystems.impl.commands.ScaleCommand;
import hr.fer.zemris.lsystems.impl.commands.SkipCommand;
import hr.fer.zemris.math.Vector2D;

/**
 * Class that implements LSystemBuilder
 */
public class LSystemBuilderImpl implements LSystemBuilder {
	
	/**
	 * Dictionary of productions
	 */
	private Dictionary<Character, String> productions;
	
	/**
	 * Dictionary of commands to be executed
	 */
	private Dictionary<Character, Command> actions;
	
	/**
	 * Unit length
	 */
	private double unitLength = 0.1;
	
	/**
	 * Scaler for unit length
	 */
	private double unitLengthDegreeScaler = 1;
	
	/**
	 * Turtle's position
	 */
	private Vector2D origin = new Vector2D(0, 0);
	
	/**
	 * Turtle's angle
	 */
	private double angle = 0;
	
	/**
	 * Axiom
	 */
	private String axiom = "";
	
	/**
	 * Constructor just for productions and commands since everything else has default value
	 */
	public LSystemBuilderImpl() {
		this.productions = new Dictionary<Character, String>();
		this.actions = new Dictionary<Character, Command>();
	}

	@Override
	public LSystem build() {
		
		return new LSystem() {

			@Override
			public void draw(int level, Painter painter) {
				Context context = new Context();
				Vector2D direction = new Vector2D(1, 0).rotated(Math.toRadians(angle)).normalised(); 
				TurtleState currentState = new TurtleState(origin, direction, Color.BLACK, unitLength * Math.pow(unitLengthDegreeScaler, level));
				context.pushState(currentState);
				char[] generated = generate(level).toCharArray();
				
				for(char generatedCharacter : generated) {
					Command command = actions.get(generatedCharacter);
					if(command != null) {
						command.execute(context, painter);
					}	
				}
			}
			
			@Override
			public String generate(int level) {
				String generated = axiom;
				
				for(int i = 0; i < level; i++) {
					StringBuilder help = new StringBuilder();
					for(int j = 0; j < generated.length(); j++) {
						String currentProduction = productions.get(generated.charAt(j));
						if(currentProduction != null) {
							help.append(currentProduction);
						} else {
							help.append(generated.charAt(j));
						}
					}
					generated = help.toString();
				}
				return generated;
			}
		};
	}

	@Override
	public LSystemBuilder configureFromText(String[] lines) {
		
		for(String line : lines) {
			//maybe it is empty - continue immediately
			if(line.equals("")) {
				continue;
			}
			
			//split by whitespace
			String[] lineData = line.split("\\s+");
			switch(lineData[0]) {
			
			case "origin":
				
				if(lineData.length != 3) {
					throw new IllegalArgumentException("Origin should have 2 parameters.");
				} 
				try {
					double x = Double.parseDouble(lineData[1]);
					double y = Double.parseDouble(lineData[2]);
					origin = new Vector2D(x, y);
				} catch(NumberFormatException ex) {
					throw new IllegalArgumentException("Could not parse to double.");
				}
				break;
				
			case "angle":
				if(lineData.length != 2) {
					throw new IllegalArgumentException("Angle should have 1 parameter.");
				} 
				try {
					double angle = Double.parseDouble(lineData[1]);
					this.angle = angle;
				} catch(NumberFormatException ex) {
					throw new IllegalArgumentException("Could not parse to double.");
				}
				break;
				
			case "unitLength":
				if(lineData.length != 2) {
					throw new IllegalArgumentException("Unit length should have 1 parameter.");
				} 
				try {
					double length = Double.parseDouble(lineData[1]);
					unitLength = length;
				} catch(NumberFormatException ex) {
					throw new IllegalArgumentException("Could not parse to double.");
				}
				break;
				
			case "unitLengthDegreeScaler":
				String[] newLineData = line.replace("/", " ").split("\\s+");
				if(newLineData.length != 3) {
					throw new IllegalArgumentException("Unit length degree scaler should have scaler");
				}
				try {
					double scaler = Double.parseDouble(newLineData[1]) / Double.parseDouble(newLineData[2]);
					unitLengthDegreeScaler = scaler;
				} catch(NumberFormatException ex) {
					throw new IllegalArgumentException("Could not parse to double.");
				}
				break;
				
			case "axiom":
				if(lineData.length != 2) {
					throw new IllegalArgumentException("Axiom should have 1 parameter.");
				}
				axiom = lineData[1];
				break;
			
			case "command":
				if(lineData.length != 3 && lineData.length != 4) {
					throw new IllegalArgumentException("Command should have 3 parameters.");
				}
				if(lineData[1].length() != 1) {
					//it is not character after "command"
					throw new IllegalArgumentException("One character should be here.");
				}
				char symbol = lineData[1].charAt(0);
				String command = lineData[2];
				if(lineData.length == 4) {
					//if it is not pop or push - prepare for registerCommand
					command += " " + lineData[3];
				}
				registerCommand(symbol, command);
				break;
				
			case "production":
				if(lineData.length != 3) {
					throw new IllegalArgumentException("Production should have 2 parameters");
				}
				if(lineData[1].length() != 1) {
					//it is not character after "production"
					throw new IllegalArgumentException("One character should be here.");
				}
				char symbolToEvaluate = lineData[1].charAt(0);
				registerProduction(symbolToEvaluate, lineData[2]);
				break;
				
			default:
				throw new IllegalArgumentException("I do not recognize this directive.");
			}
		}
		
		return this;
	}

	@Override
	public LSystemBuilder registerCommand(char symbol, String action) {
		String[] separatedAction = action.split("\\s+");
		
		switch(separatedAction[0]) {
		
		case "draw":
			try {
				double step = Double.parseDouble(separatedAction[1]);
				DrawCommand draw = new DrawCommand(step);
				actions.put(symbol, draw);
			} catch(NumberFormatException ex) {
				throw new IllegalArgumentException("Could not parse it into double");
			}
			break;
			
		case "skip":
			try {
				double step = Double.parseDouble(separatedAction[1]);
				SkipCommand skip = new SkipCommand(step);
				actions.put(symbol, skip);
			} catch(NumberFormatException ex) {
				throw new IllegalArgumentException("Could not parse it into double");
			}
			break;
			
		case "scale":
			try {
				double factor = Double.parseDouble(separatedAction[1]);
				ScaleCommand scale = new ScaleCommand(factor);
				actions.put(symbol, scale);
			} catch(NumberFormatException ex) {
				throw new IllegalArgumentException("Could not parse it into double");
			}
			break;
			
		case "rotate":
			try {
				double angle = Double.parseDouble(separatedAction[1]);
				RotateCommand rotate = new RotateCommand(angle);
				actions.put(symbol, rotate);
			} catch(NumberFormatException ex) {
				throw new IllegalArgumentException("Could not parse it into double");
			}
			break;
			
		case "push":
			actions.put(symbol, new PushCommand());
			break;
			
		case "pop":
			actions.put(symbol, new PopCommand());
			break;
			
		case "color":
			try {
				Color color = Color.decode("0x" + separatedAction[1]);
				ColorCommand colorCommand = new ColorCommand(color);
				actions.put(symbol, colorCommand);
			} catch(NumberFormatException ex) {
				throw new IllegalArgumentException("Could not parse it into color");
			}
			break;
			
		default:
			throw new IllegalArgumentException("I do not recognize it as a command.");
		}
		
		return this;
	}

	@Override
	public LSystemBuilder registerProduction(char symbol, String production) {
		productions.put(symbol, production);
		return this;
	}

	@Override
	public LSystemBuilder setAngle(double angle) {
		Objects.requireNonNull(angle);
		this.angle = angle;
		return this;
	}

	@Override
	public LSystemBuilder setAxiom(String axiom) {
		Objects.requireNonNull(axiom);
		this.axiom = axiom;
		return this;
	}

	@Override
	public LSystemBuilder setOrigin(double x, double y) {
		Objects.requireNonNull(x);
		Objects.requireNonNull(y);
		origin = new Vector2D(x, y);
		return this;
	}

	@Override
	public LSystemBuilder setUnitLength(double unitLength) {
		Objects.requireNonNull(unitLength);
		this.unitLength = unitLength;
		return this;
	}

	@Override
	public LSystemBuilder setUnitLengthDegreeScaler(double unitLengthDegreeScaler) {
		Objects.requireNonNull(unitLengthDegreeScaler);
		this.unitLengthDegreeScaler = unitLengthDegreeScaler;
		return this;
	}

}
