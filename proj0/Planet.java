/** Project 0 */

public class Planet {
    public static double G = 6.67e-11;
    public static String path = "images/";

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
              double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
	double dx = p.xxPos - xxPos;
	double dy = p.yyPos - yyPos;
	return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Planet p) {
        double distance = this.calcDistance(p);
	return G * this.mass * p.mass / (distance * distance);
    }

    public double calcForceExertedByX(Planet p) {
	double distance = this.calcDistance(p);
	return this.calcForceExertedBy(p) * (p.xxPos - this.xxPos) / distance;
    }


    public double calcForceExertedByY(Planet p) {
	double distance = this.calcDistance(p);
	return this.calcForceExertedBy(p) * (p.yyPos - this.yyPos) / distance;
    }

    public double calcNetForceExertedByX(Planet[] ps) {
	double netForceX = 0;
	for(Planet p : ps){
	    if(this.equals(p)) {
		continue;	 
	    }
	    netForceX += this.calcForceExertedByX(p);
	}
	return netForceX;
    }

     public double calcNetForceExertedByY(Planet[] ps) {
	double netForceY = 0;
	for(Planet p : ps) {
	    if(this.equals(p)) {
		continue;	 
	    }
	    netForceY += this.calcForceExertedByY(p);
	}
	return netForceY;
    }
    
    public void update(double dt, double fX, double fY) {
	double aX = fX / mass;
	double aY = fY / mass;
	
	xxVel += dt * aX;
	yyVel += dt * aY;

	xxPos += dt * xxVel;
	yyPos += dt * yyVel;
    }

    public void draw() {
	StdDraw.picture(xxPos, yyPos, path + imgFileName);
	StdDraw.show();
    }

}
