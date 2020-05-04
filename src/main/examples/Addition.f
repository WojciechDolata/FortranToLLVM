      implicit none

      integer a, b
      logical d, e
      e = .true.
      a = 2
      b = 3
      d = a .gt. b .and. e
      write(*, '(a)') a

      end