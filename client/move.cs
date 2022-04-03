using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class move : MonoBehaviour
{

    public float speed = 5f, j = 50f;
    public bool ground = true, faceRight = true;
    Attribute attribute;
    Rigidbody2D rigidbody2d;
    void Start()
    {
        rigidbody2d = this.GetComponent<Rigidbody2D>();
     
    }

    // Update is called once per frame
    void Update()
    {
        float x = Input.GetAxis ("Horizontal");
        rigidbody2d.velocity = new Vector3 (x * speed, rigidbody2d.velocity.y, 0);

      
        if(x>0 && faceRight)
        {
            Flip();
        }
        if(x<0 && !faceRight)
        {
            Flip();
        }
        if(rigidbody2d.position.y < -20.0f){
            attribute.Death();

        }

        if(Input.GetButton("Jump"))
        {
            if(ground == true){
                ground = false;

                rigidbody2d.velocity = Vector2.up * j; 
            }
               
        }

        	
        
    }
    public void Flip(){
        faceRight=!faceRight;
        Vector3 Scale;
        Scale = transform.localScale;
        Scale.x *= -1;
        transform.localScale = Scale;
    }
}
