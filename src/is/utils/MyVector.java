package is.utils;

public class MyVector
{
    public int x,y;
    public MyVector(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" +
                "x=" + x +
                ", y=" + y +
                ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if ( !(o instanceof MyVector) ) return false;
        MyVector myVector = (MyVector) o;
        return x == myVector.x && y == myVector.y;
    }//equals

}//MyVector
