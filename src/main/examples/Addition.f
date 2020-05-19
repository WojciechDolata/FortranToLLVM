      implicit none

      integer i, a, b
      b = 1


    10 if (b .le. 100) then
            b = 2*b
            goto 10
         endif


      b = 0

      end